package org.example.userservice.models.exceptions;

import org.example.exceptions.api.AlreadyExistException;

/**
 * @author Tribushko Danil
 * @since 08.11.2024
 */
public class UserByUsernameAlreadyExistException extends AlreadyExistException {
    public UserByUsernameAlreadyExistException(String username) {
        super("Пользователь с указанным именем: " + username + " уже существует");
    }
}
