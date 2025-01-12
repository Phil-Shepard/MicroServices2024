package org.example.taskservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.example.taskservice.models.dto.TaskTagDTO;
import org.example.taskservice.services.TaskTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TaskTagController {
    private final TaskTagService taskTagService;

    @Autowired
    public TaskTagController(TaskTagService taskTagService) {
        this.taskTagService = taskTagService;
    }

    @PostMapping()
    @Secured("ROLE_ADMIN")
    public ResponseEntity<TaskTagDTO> save(@RequestBody @Valid TaskTagDTO taskTagDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskTagService.save(taskTagDTO));
    }

    @PutMapping()
    @Secured("ROLE_ADMIN")
    public ResponseEntity<TaskTagDTO> update(@RequestBody @Valid TaskTagDTO taskTagDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskTagService.update(taskTagDTO));
    }

    @DeleteMapping(("/{id}"))
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        taskTagService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskTagDTO>> findAll(
            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "Номер страницы не может быть меньше 0")
            Integer page,
            @RequestParam(defaultValue = "1", name = "per_page")
            @Min(value = 1, message = "Номер страницы не может быть меньше 1")
            Integer perPage
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(taskTagService.findAll(page, perPage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskTagDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(taskTagService.findById(id));
    }
}
