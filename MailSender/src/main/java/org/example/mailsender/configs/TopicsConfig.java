package org.example.mailsender.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tribushko Danil
 * @since 29.11.2024
 * Конфигурация топиков кафки
 */
@Configuration
public class TopicsConfig {
    @Value(value = "${spring.kafka.prefix}." + "${spring.kafka.confirm-email}")
    private String confirmEmailTopicName;
    @Bean
    public NewTopic confirmEmail() {
        return new NewTopic(confirmEmailTopicName, 1, (short) 1);
    }
}
