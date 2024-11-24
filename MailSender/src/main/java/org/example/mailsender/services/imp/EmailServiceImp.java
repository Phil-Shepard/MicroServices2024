package org.example.mailsender.services.imp;

import org.example.mailsender.models.dto.EmailMessage;
import org.example.mailsender.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author Tribushko Danil
 * @since 29.11.2024
 * Сервис для отправки электронных сообщений
 */
@Component
@PropertySource("classpath:secret.properties")
public class EmailServiceImp implements EmailService {
    private JavaMailSender mailSender;
    @Value("${mail-sender.username}")
    private String username;
    @Autowired
    public EmailServiceImp(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMessage(EmailMessage emailMessage) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(emailMessage.getTo());
        message.setSubject(emailMessage.getSubject());
        message.setText(emailMessage.getText());
        message.setFrom(username);

        mailSender.send(message);
    }
}
