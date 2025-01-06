package org.example.userservice.services;

import io.jsonwebtoken.Claims;
import org.example.userservice.models.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Tribushko Danil
 * @since 01.01.2025
 * Сервис для работы с jwt токенами
 */
public interface JwtService {
    String generateToken(User user);
    boolean validateToken(String token, UserDetails user);
    Claims getClaims(String token);
}
