package org.example.userservice.models.exceptions;

import org.example.exceptions.api.BaseApiException;

/**
 * @author Tribushko Danil
 * @since 01.01.2025
 */
public class TokenNotValidException extends BaseApiException {
    public TokenNotValidException() {
        super(400, "Токен не валидный");
    }
}
