package org.example.mailsender.listeners;

import org.example.mailsender.models.dto.EmailMessage;
import org.example.mailsender.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Tribushko Danil
 * @since 29.11.2024
 * Слушатель для отправки кода подтверждения электронных адресов
 */
@Component
public class ConfirmEmailKafkaListener {
    private EmailService emailService;
    @Value(value = "${spring.kafka.prefix}." + "${spring.kafka.confirm-email}")
    private String confirmEmailTopicName;

    @Autowired
    public ConfirmEmailKafkaListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "${spring.kafka.prefix}." + "${spring.kafka.confirm-email}", groupId = "group1")
    void sendConfirmCode(String emailMessage) {
        System.out.println(confirmEmailTopicName);
        emailService.sendMessage(EmailMessage.fromJson(emailMessage));
    }
}
