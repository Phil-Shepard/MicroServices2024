package org.example.userservice.services;

import org.example.userservice.models.entities.Role;

/**
 * @author Tribushko Danil
 * @since 02.01.2025
 * Сервис для работы с ролями
 */
public interface RoleService {
    Role getUserRole();
    Role getAdminRole();
    Role getByName(String roleName);
}
