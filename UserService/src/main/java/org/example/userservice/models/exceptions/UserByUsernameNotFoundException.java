package org.example.userservice.models.exceptions;

import org.example.exceptions.api.NotFoundException;

/**
 * @author Tribushko Danil
 * @since 08.12.2024
 */
public class UserByUsernameNotFoundException extends NotFoundException {
    public UserByUsernameNotFoundException(String username) {
        super("Пользователь с указанным именем: " + username + " не найден");
    }
}
