package org.example.taskservice.repositories;

import org.example.taskservice.models.entities.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 12.01.2025
 * <p>
 * Репозиторий для работы с тегом задачи
 */
@Repository
public interface TaskTagRepository extends JpaRepository<TaskTag, Long> {
    boolean existsByName(String name);
    Optional<TaskTag> findByName(String name);
}
