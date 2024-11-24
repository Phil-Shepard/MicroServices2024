package org.example.kafkaexample.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerExample {
    private static final Logger log = LoggerFactory.getLogger(KafkaListenerExample.class);

    @KafkaListener(topics = "kafka-example", groupId = "group1")
    void listener(String message) {
        log.info("Received: {}",  message);
    }
}
