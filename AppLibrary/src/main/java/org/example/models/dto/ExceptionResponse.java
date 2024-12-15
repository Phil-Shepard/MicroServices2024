package org.example.models.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

/**
 * @author Tribushko Danil
 * @since 08.12.2024
 * Dto ответа при возникновении исключения
 */
public class ExceptionResponse implements AppDto {
    private int statusCode;
    private String message;
    private LocalDateTime timestamp;

    public ExceptionResponse(int statusCode, String message, LocalDateTime timestamp) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = timestamp;
    }

    @Override
    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
