package org.example.userservice.controllers;

import jakarta.validation.Valid;
import org.example.userservice.models.dto.ConfirmEmailDto;
import org.example.userservice.models.dto.JwtTokenDto;
import org.example.userservice.models.dto.SignInRequest;
import org.example.userservice.models.dto.SignUpDto;
import org.example.userservice.models.entities.ConfirmEmailRequest;
import org.example.userservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtTokenDto> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.signIn(signInRequest));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<JwtTokenDto> signUp(@Valid @RequestBody SignUpDto signUpDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signUp(signUpDto));
    }

    @PatchMapping("/confirm")
    public ResponseEntity<JwtTokenDto> confirm(@Valid @RequestBody ConfirmEmailDto request) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.confirmEmail(request));
    }

    @PostMapping("/send/confirm-code")
    public ResponseEntity<?> sendCode(@RequestParam String email) {
        authService.sendCode(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
