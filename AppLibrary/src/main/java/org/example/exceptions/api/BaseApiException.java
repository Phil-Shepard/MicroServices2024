package org.example.exceptions.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * @author Tribushko Danil
 * @since 08.12.2024
 * Базовый класс исключений приложения
 */
public class BaseApiException extends RuntimeException {
    protected int statusCode;
    protected String message;
    protected LocalDateTime timestamp;
    protected Logger log;

    public BaseApiException(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.log = LoggerFactory.getLogger(this.getClass());

        String errorLog = String.format("%s: %s", statusCode, message);

        log.warn(errorLog);
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
