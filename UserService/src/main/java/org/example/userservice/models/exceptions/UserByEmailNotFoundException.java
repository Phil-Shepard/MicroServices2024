package org.example.userservice.models.exceptions;


import org.example.exceptions.api.NotFoundException;

/**
 * @author Tribushko Danil
 * @since 08.12.2024
 */
public class UserByEmailNotFoundException extends NotFoundException {
    public UserByEmailNotFoundException(String email) {
        super("Пользователь с указанным электронным адресом: " + email + " не найден");
    }
}
