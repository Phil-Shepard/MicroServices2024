package org.example.kafkaexample.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tribuhko Danil
 * @since 24.10.2024
 * Конфигурация топиков кафки
 */
@Configuration
public class KafkaTopicsConfig {
    @Bean
    public NewTopic topic1() {
        return new NewTopic("kafka-example", 1, (short) 1);
    }
}
