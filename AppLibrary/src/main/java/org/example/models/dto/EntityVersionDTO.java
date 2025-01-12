package org.example.models.dto;

import java.time.LocalDateTime;

public abstract class EntityVersionDTO extends EntityDTO {
    protected LocalDateTime creationDate;
    protected LocalDateTime updateDate;

    public EntityVersionDTO(Long id, LocalDateTime creationDate, LocalDateTime updateDate) {
        super(id);
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }
}
