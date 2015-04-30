package com.kawal;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;

import java.util.Map;

public class Receiver implements MessageListener {

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }

    @Override
    public void onMessage(Message message) {
        MessageProperties messageProperties = message.getMessageProperties();

        Map<String, Object> headers = messageProperties.getHeaders();
        System.out.println("Headers...");
        for (Map.Entry<String, Object> entry : headers.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        System.out.println("================================");

        byte[] body = message.getBody();
        System.out.println("Body <" + new String(body) + ">");

    }
}
