package org.example.taskservice.repositories;

import org.example.taskservice.models.entities.TaskTag;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskTagPaginationRepository extends PagingAndSortingRepository<TaskTag, Long> {
}
