package org.example.userservice.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.example.models.dto.UserDTO;
import org.example.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable
                                           @Min(value = 1, message = "Идентификатор пользователя должен быть больше 0")
                                           Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> findAll(
            @RequestParam(required = false, defaultValue = "0")
            @Min(value = 0, message = "Номер страницы должен быть положительным целым чилом")
            Integer page,
            @RequestParam(required = false, defaultValue = "1", name = "per_page")
            @Min(value = 1, message = "Количество записей на одной странице должен быть положительным целым чилом")
            Integer perPage,
            @RequestParam(required = false, defaultValue = "USER", name = "role_name")
            String roleName
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(page, perPage, roleName));
    }

    @PutMapping("")
    public ResponseEntity<UserDTO> update(
            @Valid
            @RequestBody
            UserDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable
                                    @Min(value = 1, message = "Идентификатор пользователя должен быть больше 0")
                                    Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
