package org.example.taskservice.models.exceptions;

import org.example.exceptions.api.AlreadyExistException;

/**
 * @author Tribushko Danil
 * @since 12.01.2025
 */
public class TaskTagAlreadyExistsException extends AlreadyExistException {
    public TaskTagAlreadyExistsException(String name) {
        super("Тег с указанным именем: " + name + " уже создане");
    }
}
