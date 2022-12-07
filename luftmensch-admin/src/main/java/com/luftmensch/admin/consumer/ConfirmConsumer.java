package com.luftmensch.admin.consumer;

import com.luftmensch.framework.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ConfirmConsumer {

    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitListener(queues = ConfirmConfig.CONFIRM_QUEUE)
    public void receiveConfirmMessage(Message message) {
        String msg = new String(message.getBody());
        log.info("接收到的队列confirm.queue消息：{}", msg);
        //分布式锁setnx
        String uuid = UUID.randomUUID().toString();
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid, 3, TimeUnit.SECONDS);

        if (lock) {
            //修改锁
            String lock1 = (String) redisTemplate.opsForValue().get("lock");
            //释放锁
            if (lock1.equals(uuid))
            redisTemplate.delete("lock");
        } else {
            //重复调用此方法
        }

        //事务
        redisTemplate.multi();
        redisTemplate.discard();
        redisTemplate.exec();

        //监控
        redisTemplate.watch(message);
    }

    @RabbitListener(queues = ConfirmConfig.BACKUP_QUEUE)
    public void receiveBackupMessage(Message message) {
        String msg = new String(message.getBody());
        log.info("发现备份消息backup.queue消息：{}", msg);
    }

    @RabbitListener(queues = ConfirmConfig.WARRING_QUEUE)
    public void receiveWarringMessage(Message message) {
        String msg = new String(message.getBody());
        log.info("报警发现不可路有消息warring.queue消息：{}", msg);
    }
}
