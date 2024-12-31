package org.example.exceptions.api;

/**
 * @author Tribushko Danil
 * @since 08.12.2024
 * Базовый класс для исключений, которые указывают на наличие объекта в бд
 */
public abstract class AlreadyExistException extends BaseApiException {
    protected AlreadyExistException(String message) {
        super(409, message);
    }
}
