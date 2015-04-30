package com.kawal;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = SpringApplication.run(RabbitMQDemoConfig.class, args);
        TestRabbit testRabbit = ctx.getBean(TestRabbit.class);
        System.out.println("Sending simple text message...");
        testRabbit.sendStringMessage();

        System.out.println("Sending complex Message Object...");
        testRabbit.sendMessage();
    }
}
