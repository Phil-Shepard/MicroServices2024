package org.example.userservice.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 30.11.2024
 * Сущность роли пользователя
 */
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
