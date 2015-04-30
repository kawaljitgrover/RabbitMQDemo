package com.kawal;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TestRabbit {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Receiver receiver;

    public void sendStringMessage() throws InterruptedException {
        System.out.println("Waiting five seconds...");
        Thread.sleep(5000);
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(RabbitMQDemoConfig.queueName, "Hello from RabbitMQ!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

    public void sendMessage() throws InterruptedException {
        System.out.println("Waiting five seconds...");
        Thread.sleep(5000);
        System.out.println("Sending message...");
        Message message = MessageBuilder.withBody("Customized Messages".getBytes())
                .setHeaderIfAbsent("header1", "My header")
                .setReplyTo("something")
                .setCorrelationId("something".getBytes())
                .build();
        rabbitTemplate.send(RabbitMQDemoConfig.queueName, message);
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }



}
