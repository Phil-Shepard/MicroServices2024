package org.example.taskservice.models.dto;

import jakarta.validation.constraints.NotBlank;
import org.example.models.dto.EntityVersionDTO;

import java.time.LocalDateTime;

/**
 * @author Tribushko Danil
 * @since 12.01.2025
 * Dto тэга задачи
 */
public class TaskTagDTO extends EntityVersionDTO {
    @NotBlank(message = "Название тега задачи, не может быть пустым")
    private String name;

    public TaskTagDTO(
            Long id,
            String name,
            LocalDateTime creationDate,
            LocalDateTime updateDate
    ) {
        super(id, creationDate, updateDate);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
