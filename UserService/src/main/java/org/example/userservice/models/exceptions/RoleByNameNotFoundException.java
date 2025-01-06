package org.example.userservice.models.exceptions;

import org.example.exceptions.api.NotFoundException;

/**
 * @author Tribushko Danil
 * @since 02.01.2025
 */
public class RoleByNameNotFoundException extends NotFoundException {
    public RoleByNameNotFoundException(String roleName) {
        super("Роль: " + roleName + " не найдена");
    }
}
