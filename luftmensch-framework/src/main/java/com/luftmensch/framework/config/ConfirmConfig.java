package com.luftmensch.framework.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;


@Configuration
public class ConfirmConfig {

    //交换机
    public static final String CONFIRM_EXCHANGE = "confirm_exchange";

    //备份交换机
    public static final String BACKUP_EXCHANGE = "backup_exchange";

    //队列
    public static final String CONFIRM_QUEUE = "confirm_queue";

    //备份队列
    public static final String BACKUP_QUEUE = "backup_queue";

    //报错队列
    public static final String WARRING_QUEUE = "warring_queue";

    //routingKey
    public static final String CONFIRM_ROUTING_KEY = "key1";

    //直接交换机
    @Bean("confirmExchange")
    public DirectExchange confirmExchange() {
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("alternate-exchange", BACKUP_EXCHANGE);
        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE)
                .withArguments(arguments)
                .build();
    }

    //备份交换机
    @Bean("backupExchange")
    public FanoutExchange backupExchange() {
        return ExchangeBuilder.fanoutExchange(BACKUP_EXCHANGE)
                .build();
    }

    //队列
    @Bean("confirmQueue")
    public Queue confirmQueue() {
        return QueueBuilder.durable(CONFIRM_QUEUE)
                .build();
    }

    //备份队列
    @Bean("backupQueue")
    public Queue backupQueue() {
        return QueueBuilder.durable(BACKUP_QUEUE)
                .build();
    }

    //报错队列
    @Bean("warringQueue")
    public Queue warringQueue() {
        return QueueBuilder.durable(WARRING_QUEUE)
                .build();
    }

    //绑定关系
    @Bean
    public Binding confirmQueueBindConfirmExchange(@Qualifier("confirmQueue") Queue confirmQueue,
                                                   @Qualifier("confirmExchange") DirectExchange confirmExchange) {
        return BindingBuilder.bind(confirmQueue)
                .to(confirmExchange)
                .with(CONFIRM_ROUTING_KEY);
    }

    //备份交换机绑定备份队列
    @Bean
    public Binding backupQueueQueueBindBackupExchange(@Qualifier("backupQueue") Queue backupQueue,
                                                      @Qualifier("backupExchange") FanoutExchange backupExchange) {
        return BindingBuilder.bind(backupQueue)
                .to(backupExchange);
    }

    //备份交换机绑定报错队列
    @Bean
    public Binding warringQueueBindBackupExchange(@Qualifier("warringQueue") Queue warringQueue,
                                                  @Qualifier("backupExchange") FanoutExchange backupExchange) {
        return BindingBuilder.bind(warringQueue)
                .to(backupExchange);
    }
}
