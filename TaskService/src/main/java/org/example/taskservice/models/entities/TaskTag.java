package org.example.taskservice.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "task_tags")
public class TaskTag extends EntityVersion {
    private String name;

    public TaskTag() {
        super();
    }

    public TaskTag(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
