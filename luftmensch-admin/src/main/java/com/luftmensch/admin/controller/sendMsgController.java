package com.luftmensch.admin.controller;

import com.luftmensch.framework.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send/msg")
@Slf4j
public class sendMsgController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/confirm/{msg}")
    public void sendConfirmMessage(@PathVariable("msg") String message) {
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE, ConfirmConfig.CONFIRM_ROUTING_KEY, message);
        log.info("发送消息内容：{}", message);
    }
}
