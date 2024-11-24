package org.example.kafkaexample.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Tribushko Danil
 * @since 24.11.204
 * Сервис для отправки сообщений в кафку
 */
@Service
public class KafkaSender {
    private final Logger log;
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.log = LoggerFactory.getLogger(this.getClass());
    }

    public void sendMessage(String topic, String message) {
        log.info("Sending message: {} to topic: {}", message, topic);
        kafkaTemplate.send(topic, message);
    }
}
