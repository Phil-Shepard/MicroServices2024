package org.example.taskservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.exceptions.api.BaseApiException;
import org.example.models.dto.UserDTO;
import org.example.taskservice.models.dto.SecurityUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;

/**
 * @author Tribushko Danil
 * @since 12.01.2025
 * Фильтер для jwt токена
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer";
    private String userFromTokenUrl;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            doFilter(request, response, filterChain);
            return;
        }
        String token = authHeader.substring(BEARER_PREFIX.length() + 1);
        UserDTO user = getUserFromToken(token);
        String username = user.getUsername();
        SecurityUser securityUser = new SecurityUser(user);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    securityUser, null, securityUser.getAuthorities()
            );
            context.setAuthentication(authToken);
            SecurityContextHolder.setContext(context);
        }
        doFilter(request, response, filterChain);
    }

    private UserDTO getUserFromToken(String token){
        try {
            HttpURLConnection conn = (HttpURLConnection) URI.create(userFromTokenUrl).toURL().openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            try (OutputStream out = conn.getOutputStream()) {
                byte[] input = String.format("{\"token\":\"%s\"}", token).getBytes();
                out.write(input, 0, input.length);
            }
            StringBuilder response = getStringBuilder(conn);
            return new ObjectMapper().readValue(response.toString(), UserDTO.class);
        } catch (IOException e) {
            throw new BaseApiException(400, e.getMessage());
        }
    }

    private static StringBuilder getStringBuilder(HttpURLConnection conn) {
        StringBuilder response = new StringBuilder();
        String inputLine;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
        } catch (IOException e) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                int statusCode = conn.getResponseCode();
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                throw new BaseApiException(statusCode, response.toString());
            } catch (IOException ex) {
                throw new BaseApiException(400, ex.getLocalizedMessage());
            }
        }
        return response;
    }
}
