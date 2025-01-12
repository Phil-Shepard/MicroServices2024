package org.example.userservice.models.exceptions;

import org.example.exceptions.api.BaseApiException;

/**
 * @author Tribushko Danil
 * @since 02.01.2025
 */
public class PasswordNotConfirmException extends BaseApiException {
    public PasswordNotConfirmException() {
        super(400, "Пароли не совпадают");
    }
}
