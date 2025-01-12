package org.example.exceptions;

/**
 * @author Tribushko Danil
 * @since 19.12.2024
 */
public class ToLessThanFromException extends AppException {
    public ToLessThanFromException() {
        super("Значение \"до\" меньше чем значение \"от\"");
    }
}
