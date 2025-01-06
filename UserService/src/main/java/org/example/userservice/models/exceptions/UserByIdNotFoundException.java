package org.example.userservice.models.exceptions;


import org.example.exceptions.api.NotFoundException;

/**
 * @author Tribushko Danil
 * @since 19.12.2024
 */
public class UserByIdNotFoundException extends NotFoundException {
    public UserByIdNotFoundException(Long id) {
        super("Пользователь с указанным id: " + id + " не найден");
    }
}
