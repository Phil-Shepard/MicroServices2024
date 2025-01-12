package org.example.taskservice.services;

import jakarta.validation.constraints.Min;
import org.example.taskservice.models.dto.TaskTagDTO;

import java.util.List;

public interface TaskTagService extends EntityService<TaskTagDTO> {
    List<TaskTagDTO> findAll(
            @Min(value = 0, message = "Номер страницы не может быть меньше 0")
            Integer page,
            @Min(value = 1, message = "Номер страницы не может быть меньше 1")
            Integer perPage
    );
}
