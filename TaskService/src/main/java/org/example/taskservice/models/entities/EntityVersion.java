package org.example.taskservice.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

/**
 * @author Tribushko Danil
 * @since 12.12.2024
 * Общий клас для сущностей, которые имеют версионность
 */
@MappedSuperclass
public abstract class EntityVersion extends EntityCreationDate {
    @Column(nullable = false, name = "update_date")
    protected LocalDateTime updateDate;
    @Column(nullable = false, name = "version")
    @ColumnDefault("0")
    protected Integer version;

    public EntityVersion() {
        super();
        this.updateDate = LocalDateTime.now();
        this.version = 0;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void update() {
        this.updateDate = LocalDateTime.now();
        this.version++;
    }
}
