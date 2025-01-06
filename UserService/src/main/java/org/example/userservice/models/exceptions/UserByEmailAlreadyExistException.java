package org.example.userservice.models.exceptions;

import org.example.exceptions.api.AlreadyExistException;

/**
 * @author Tribushko Danil
 * @since 08.12.2024
 */
public class UserByEmailAlreadyExistException extends AlreadyExistException {
    public UserByEmailAlreadyExistException(String email) {
        super("Пользователь с указанным электронным адресом: " + email + " уже существует");
    }
}
