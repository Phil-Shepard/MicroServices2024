package org.example.models.dto;

/**
 * @author Tribushko Danil
 * @since 08.12.2024
 * Dto роли пользователя
 */
public class RoleDTO {
    private String name;

    public RoleDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
