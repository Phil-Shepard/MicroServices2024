package org.example.mailsender.services.imp;

import jakarta.annotation.PostConstruct;
import org.example.mailsender.services.EmailService;
import org.example.models.dto.EmailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger log;
    @Value("${mail-sender.username}")
    private String username;
    @Autowired
    public EmailServiceImp(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @PostConstruct
    public void init(){
        log = LoggerFactory.getLogger(this.getClass());
    }

    @Override
    public void sendMessage(EmailMessage emailMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        String to = emailMessage.getTo();
        String subject = emailMessage.getSubject();
        String text = emailMessage.getText();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom(username);
        log.info(String.format("Send email to %s: object=%s text=%s", to, subject, text));
        mailSender.send(message);
    }
}
