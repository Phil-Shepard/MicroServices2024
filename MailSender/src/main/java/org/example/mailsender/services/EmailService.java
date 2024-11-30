package org.example.mailsender.services;

import org.example.mailsender.models.dto.EmailMessage;

/**
 * @author Tribushko Danil
 * @since 24.11.2024
 * Сервис для отправки электронных писемС
 */
public interface EmailService {
    void sendMessage(EmailMessage emailMessage);
}
