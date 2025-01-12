package org.example.userservice.services;

import jakarta.validation.Valid;
import org.example.models.dto.UserDTO;
import org.example.userservice.models.dto.ConfirmEmailDto;
import org.example.userservice.models.dto.JwtTokenDto;
import org.example.userservice.models.dto.SignInRequest;
import org.example.userservice.models.dto.SignUpDto;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Tribushko Danil
 * @since 31.12.2024
 * Сервис для авторизации и регистрации пользователя
 */
public interface AuthService {
    JwtTokenDto signIn(@Valid SignInRequest signInRequest);
    JwtTokenDto signUp(@Valid SignUpDto signUpDto);
    UserDTO getUserFromToken(@Valid JwtTokenDto token);
    JwtTokenDto confirmEmail(@Valid ConfirmEmailDto confirmEmailDto);
    void sendCode(@RequestParam String email);
}
