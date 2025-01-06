package org.example.userservice.models.exceptions;

import org.example.exceptions.api.NotFoundException;

/**
 * @author Tribushko Danil
 * @since 03.01.2024
 */
public class ConfirmEmailCodeNotFoundException extends NotFoundException {
    public ConfirmEmailCodeNotFoundException(String email, String code) {
        super("Код подтверждения: " + code + " не найден для пользователя с электронным адресом: " + email);
    }
}
