package org.example.userservice.services;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.example.models.dto.UserDTO;
import org.example.userservice.models.entities.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author Tribushko Danil
 * @since 08.12.2024
 * Сервис для работы с пользователями
 */
public interface UserService extends EntityService<UserDTO>, UserDetailsService {
    UserDTO findByEmail(
            @Email
            @Size(
                    min = 7,
                    max = 50,
                    message = "Электронный адрес пользователя должен содержать от 7 до 50 символов")
            String email
    );


    List<UserDTO> findAll(
            @Min(value = 0, message = "Номер страницы должен быть больше нуля")
            Integer page,
            @Min(value = 1, message = "Количество сущностей на одной странице должно быть больше еденицы")
            Integer perPage,
            String role
    );
}
