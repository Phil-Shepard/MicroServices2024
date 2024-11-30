package org.example.mailsender.models.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.dto.AppDto;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 28.11.2024
 * Класс dto сообщения для отправки на email
 */
public class EmailMessage implements AppDto {
    private String to;
    private String subject;
    private String text;

    public EmailMessage(String to, String subject, String text) {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public EmailMessage() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailMessage that = (EmailMessage) o;
        return Objects.equals(to, that.to) && Objects.equals(subject, that.subject) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, subject, text);
    }

    @Override
    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static EmailMessage fromJson(String json) {
        try {
            return new ObjectMapper().readValue(json, EmailMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
