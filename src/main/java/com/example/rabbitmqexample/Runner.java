package com.example.rabbitmqexample;

import java.util.concurrent.TimeUnit;

import com.example.rabbitmqexample.rabbitmq.Receiver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(RabbitmqExampleApplication.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ Sanjay!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}