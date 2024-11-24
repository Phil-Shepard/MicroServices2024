package org.example.kafkaexample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public ExampleService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("kafka-example", message);
    }
}
