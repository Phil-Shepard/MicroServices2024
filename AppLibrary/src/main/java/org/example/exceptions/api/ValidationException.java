package org.example.exceptions.api;

/**
 * @author Tribushko Danil
 * @since 22.12.2024
 */
public class ValidationException extends BaseApiException {
    public ValidationException(String message) {
        super(422, message);
    }
}
