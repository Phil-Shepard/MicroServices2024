package org.example.taskservice.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class EntityCreationDate extends BaseEntity {
    @Column(nullable = false, name = "creation_date", updatable = false)
    protected LocalDateTime creationDate;

    public EntityCreationDate() {
        this.creationDate = LocalDateTime.now();
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
