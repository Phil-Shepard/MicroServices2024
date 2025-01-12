package org.example.taskservice.models.exceptions;

import org.example.exceptions.api.NotFoundException;

/**
 * @author Tribushko Danil
 * @since 12.01.2024
 */
public class TaskTagByIdNotFoundException extends NotFoundException {
    public TaskTagByIdNotFoundException(Long id) {
        super("Тег задачи с указанным id: " + id + " не найден");
    }
}
