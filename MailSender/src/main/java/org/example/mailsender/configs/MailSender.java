package org.example.mailsender.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author Tribushko Danil
 * @since 29.11.2024
 * Конфигурация отправщика электронных сообщений
 */
@Configuration
@PropertySource("classpath:secret.properties")
public class MailSender {
    @Value("${mail-sender.username}")
    private String username;
    @Value("${mail-sender.password}")
    private String password;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        mailSender.setHost("smtp.yandex.ru");
        mailSender.setPort(587);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
