package org.example.userservice.services.imp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.userservice.models.entities.Role;
import org.example.userservice.models.entities.User;
import org.example.userservice.services.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@PropertySource("classpath:secret.properties")
public class JwtServiceImp implements JwtService {
    @Value("${jwt.secret}")
    private String secret;

    @Override
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", user.getRoles().stream().map(Role::getName).toList());
        claims.put("email", user.getEmail());
        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + 1000 * 3600 * 24);
        return Jwts.builder()
                .signWith(getSecretKey())
                .claims(claims)
                .issuedAt(issuedAt)
                .expiration(expiration)
                .subject(user.getUsername())
                .compact();
    }

    @Override
    public boolean validateToken(String token, UserDetails user) {
        Claims claims = getClaims(token);
        return Objects.equals(claims.getSubject(), user.getUsername()) && new Date().before(claims.getExpiration());
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
}
