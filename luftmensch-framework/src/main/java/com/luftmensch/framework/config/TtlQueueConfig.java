package com.luftmensch.framework.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 *  TTL队列，配置文件类
 */
@Configuration
public class TtlQueueConfig {

    //普通交换机
    public static final String X_EXCHANGE = "X";
    //死信交换机
    public static final String Y_DEAD_LETTER_EXCHANGE = "Y";
    //普通队列名称
    public static final String QUEUE_A = "QA";
    public static final String QUEUE_B = "QB";
    //死信队列名称
    public static final String DEAD_LETTER_QUEUE_D = "QD";

    //声明X交换机（普通交换机）
    @Bean("xExchange")
    public DirectExchange xExchange() {
        return new DirectExchange(X_EXCHANGE);
    }

    //声明Y交换机（死信交换机）
    @Bean("yExchange")
    public DirectExchange yExchange() {
        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }

    //声明普通队列
    @Bean("queueA")
    public Queue queueA() {
        Map<String, Object> arguments = new HashMap<>(4);
        //设置死信交换机
        arguments.put("x-dead-letter-exchange", DEAD_LETTER_QUEUE_D);
        //设置死信routingKey
        arguments.put("x-dead-letter-routing-key", "YD");
        //设置TTL，单位是ms
        arguments.put("x-message-ttl", 10000);

        return QueueBuilder.durable(QUEUE_A)
                .withArguments(arguments)
                .build();
    }

    @Bean("queueB")
    public Queue queueB() {
        Map<String, Object> arguments = new HashMap<>(4);
        //设置死信交换机
        arguments.put("x-dead-letter-exchange", DEAD_LETTER_QUEUE_D);
        //设置死信routingKey
        arguments.put("x-dead-letter-routing-key", "YD");
        //设置TTL，单位是ms
        arguments.put("x-message-ttl", 40000);

        return QueueBuilder.durable(QUEUE_B)
                .withArguments(arguments)
                .build();
    }

    //声明死信队列
    @Bean("queueD")
    public Queue queueD() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE_D)
                .build();
    }

    //绑定QA和X
    @Bean
    public Binding queueABindingX(@Qualifier("queueA") Queue queueA, @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queueA)
                .to(xExchange)
                .with("XA");
    }

    //绑定QB和X
    @Bean
    public Binding queueBBindingX(@Qualifier("queueB") Queue queueB, @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queueB)
                .to(xExchange)
                .with("XB");
    }

    //绑定QD和Y
    @Bean
    public Binding queueDBindingY(@Qualifier("queueD") Queue queueD, @Qualifier("yExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queueD)
                .to(xExchange)
                .with("YD");
    }
}
