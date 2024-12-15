package org.example.exceptions;

/**
 * @author Tribushko Danil
 * @since 08.12.2024
 * Базовый класс для исключений, которые указывают на отсутсвие объекта
 */
public abstract class NotFoundException extends BaseApiException {
    protected NotFoundException(String message) {
        super(404, message);
    }
}
