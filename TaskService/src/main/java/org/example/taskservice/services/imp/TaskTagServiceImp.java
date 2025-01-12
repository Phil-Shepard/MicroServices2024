package org.example.taskservice.services.imp;

import org.example.taskservice.aspects.annotations.IsAdmin;
import org.example.taskservice.models.dto.TaskTagDTO;
import org.example.taskservice.models.entities.TaskTag;
import org.example.taskservice.models.exceptions.TaskTagAlreadyExistsException;
import org.example.taskservice.models.exceptions.TaskTagByIdNotFoundException;
import org.example.taskservice.models.mappers.TaskTagMapper;
import org.example.taskservice.repositories.TaskTagPaginationRepository;
import org.example.taskservice.repositories.TaskTagRepository;
import org.example.taskservice.services.TaskTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskTagServiceImp implements TaskTagService {
    private final TaskTagRepository taskTagRepository;
    private final TaskTagPaginationRepository taskTagPaginationRepository;

    @Autowired
    public TaskTagServiceImp(
            TaskTagRepository taskTagRepository,
            TaskTagPaginationRepository taskTagPaginationRepository
    ) {
        this.taskTagRepository = taskTagRepository;
        this.taskTagPaginationRepository = taskTagPaginationRepository;
    }

    @Override
    public List<TaskTagDTO> findAll(Integer page, Integer perPage) {
        return taskTagPaginationRepository.findAll(PageRequest.of(page, perPage))
                .map(TaskTagMapper::mapFromEntity)
                .toList();
    }

    @Override
    public TaskTagDTO findById(Long id) {
        return TaskTagMapper.mapFromEntity(findEntityById(id));
    }

    @Override
    @IsAdmin
    public TaskTagDTO save(TaskTagDTO taskTagDTO) {
        TaskTag entity = TaskTagMapper.mapToEntity(taskTagDTO);
        String name = entity.getName();
        if (taskTagRepository.existsByName(name)) {
            throw new TaskTagAlreadyExistsException(name);
        }
        entity = taskTagRepository.save(entity);
        return TaskTagMapper.mapFromEntity(entity);
    }

    @Override
    @IsAdmin
    public void deleteById(Long id) {
        taskTagRepository.delete(findEntityById(id));
    }

    @Override
    @IsAdmin
    public TaskTagDTO update(TaskTagDTO entity) {
        TaskTag tag = findEntityById(entity.getId());
        String name = entity.getName();
        if (name != null) {
            if (taskTagRepository.existsByName(name)) {
                throw new TaskTagAlreadyExistsException(name);
            }
            else{
                tag.setName(name);
                tag.update();
            }
        }
        return TaskTagMapper.mapFromEntity(taskTagRepository.save(tag));
    }

    private TaskTag findEntityById(Long id) {
        return taskTagRepository.findById(id)
                .orElseThrow(() -> new TaskTagByIdNotFoundException(id));
    }
}
