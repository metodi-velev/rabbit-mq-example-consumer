package com.rabbitmq.example.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private PersonRepository personRepository;

    @Autowired
    public MessageListener(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(CustomMessage message) {
        System.out.println(message);
        personRepository.save(new Person(message.getMessage()));
    }

}
