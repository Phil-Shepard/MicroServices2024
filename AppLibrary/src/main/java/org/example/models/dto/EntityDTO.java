package org.example.models.dto;

public abstract class EntityDTO {
    protected Long id;

    public EntityDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
