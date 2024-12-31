package org.example.exceptions;

/**
 * @author Tribushko Danil
 * @since 19.12.2024
 * Базовый класс исключения приложения
 */
public abstract class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }
}
