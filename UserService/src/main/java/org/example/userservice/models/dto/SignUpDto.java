package org.example.userservice.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * @author Tribushko Danil
 * @since 19.12.2024
 * Запрос на создание пользователя
 */
public class SignUpDto {
    @Pattern(
            regexp = "^[0-9a-zA-Z]{1,15}[-,.]{0,1}[0-9a-zA-Z]{1,15}[@]{1}[a-z]{1,8}[.]{0,1}[a-z]{2,4}$",
            message = "В поле \"email\", должен быть указан электронный адресс пользователя"
    )
    private String email;
    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Size(min = 8, message = "Имя пользователя должно содержать минимум 8 символов")
    private String username;
    @NotBlank(message = "Пароль пользователя не может быть пустым")
    @Size(min = 8, message = "Пароль должен содержать минимум 8 символов")
    private String password;
    @NotBlank(message = "Пароль для подтверждения пользователя не может быть пустым")
    @Size(min = 8, message = "Пароль для подтверждения должен содержать минимум 8 символов")
    private String confirmPassword;

    public SignUpDto(String email, String username, String password, String confirmPassword) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
