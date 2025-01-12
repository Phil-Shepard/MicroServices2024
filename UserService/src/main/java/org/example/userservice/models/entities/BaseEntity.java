package org.example.userservice.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * @author Tribushko Danil
 * @since 12.12.2024
 * Базовый класс сущностей
 */
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
