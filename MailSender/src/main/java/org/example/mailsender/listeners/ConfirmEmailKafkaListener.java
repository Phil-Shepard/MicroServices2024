package org.example.mailsender.listeners;

import org.example.mailsender.services.EmailService;
import org.example.models.dto.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public ConfirmEmailKafkaListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "${kafka.prefix}." + "${kafka.confirm-email}", groupId = "group1")
    void sendConfirmCode(String emailMessage) {
        emailService.sendMessage(EmailMessage.fromJson(emailMessage));
    }
}
