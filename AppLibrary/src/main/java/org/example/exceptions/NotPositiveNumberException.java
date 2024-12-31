package org.example.exceptions;

/**
 * @author Tribushko Danil
 * @since 19.12.2024
 */
public class NotPositiveNumberException extends AppException {
    public NotPositiveNumberException() {
        super("Значение не является позитивным целым числом");
    }
}
