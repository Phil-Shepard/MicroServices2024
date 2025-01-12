package org.example.taskservice.models.mappers;

import org.example.taskservice.models.dto.TaskTagDTO;
import org.example.taskservice.models.entities.TaskTag;

public class TaskTagMapper {
    public static TaskTagDTO mapFromEntity(TaskTag tag) {
        return new TaskTagDTO(tag.getId(), tag.getName(), tag.getCreationDate(), tag.getUpdateDate());
    }

    public static TaskTag mapToEntity(TaskTagDTO dto) {
        TaskTag entity = new TaskTag();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
