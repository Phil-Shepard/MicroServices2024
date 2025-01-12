package org.example.userservice.models.dto;

/**
 * @author Tribushko Danil
 * @since 02.01.2025
 * Dto ответа на получение пользователя
 */
public class JwtTokenDto {
    private String token;

    public JwtTokenDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
