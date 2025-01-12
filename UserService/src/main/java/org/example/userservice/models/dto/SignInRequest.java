package org.example.userservice.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @author Tribushko Danil
 * @since 31.12.2024
 * Запрос на авторизацию пользователя
 */
public class SignInRequest {
    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Size(min = 8, message = "Имя пользователя должно содержать минимум 8 символов")
    private String username;
    @NotBlank(message = "Пароль пользователя не может быть пустым")
    @Size(min = 8, message = "Пароль должен содержать минимум 8 символов")
    private String password;

    public SignInRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
