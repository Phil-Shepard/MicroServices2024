package org.example.userservice.services.imp;

import org.example.models.dto.EmailMessage;
import org.example.models.dto.UserDTO;
import org.example.models.enums.UserState;
import org.example.userservice.models.dto.ConfirmEmailDto;
import org.example.userservice.models.dto.JwtTokenDto;
import org.example.userservice.models.dto.SignInRequest;
import org.example.userservice.models.dto.SignUpDto;
import org.example.userservice.models.entities.ConfirmEmailRequest;
import org.example.userservice.models.entities.Role;
import org.example.userservice.models.entities.User;
import org.example.userservice.models.exceptions.*;
import org.example.userservice.models.mappers.UserMapper;
import org.example.userservice.repositories.ConfirmEmailRequestRepository;
import org.example.userservice.repositories.UserRepository;
import org.example.userservice.services.AuthService;
import org.example.userservice.services.JwtService;
import org.example.userservice.services.RoleService;
import org.example.userservice.services.UserService;
import org.example.userservice.utils.ConfirmEmailCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Tribushko Danil
 * @since 02.01.2025
 */
@Service
public class AuthServiceImp implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ConfirmEmailRequestRepository confirmEmailRequestRepository;

    @Value("${kafka.confirm-email}")
    private String confirmEmailTopic;

    @Autowired
    public AuthServiceImp(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            RoleService roleService,
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            KafkaTemplate<String, String> kafkaTemplate,
            ConfirmEmailRequestRepository confirmEmailRequestRepository,
            UserService userService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.kafkaTemplate = kafkaTemplate;
        this.confirmEmailRequestRepository = confirmEmailRequestRepository;
        this.userService = userService;
    }

    @Override
    public JwtTokenDto signIn(SignInRequest signInRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInRequest.getUsername(),
                            signInRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw e;
        }
        User user = userRepository.findByUsername(signInRequest.getUsername())
                .orElseThrow(() -> new UserByUsernameNotFoundException(signInRequest.getUsername()));
        String token = jwtService.generateToken(user);
        return new JwtTokenDto(token);
    }

    @Override
    public JwtTokenDto signUp(SignUpDto signUpDto) {
        String email = signUpDto.getEmail();
        String username = signUpDto.getUsername();
        String password = signUpDto.getPassword();
        String confirmPassword = signUpDto.getConfirmPassword();

        if (userRepository.existsByEmail(email)) {
            throw new UserByEmailAlreadyExistException(email);
        }

        if (userRepository.existsByUsername(username)) {
            throw new UserByUsernameAlreadyExistException(username);
        }

        if (!Objects.equals(password, confirmPassword)) {
            throw new PasswordNotConfirmException();
        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getUserRole());

        User user = new User()
                .builder()
                .email(email)
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles(roles)
                .state(UserState.NON_CONFIRM)
                .build();

       userRepository.save(user);

        String code = ConfirmEmailCodeUtil.generateCode();
        while (confirmEmailRequestRepository.existsByEmailAndCode(email, code)) {
            code = ConfirmEmailCodeUtil.generateCode();
        }

        ConfirmEmailRequest confirmEmailRequest = new ConfirmEmailRequest(email, code);
        confirmEmailRequestRepository.save(confirmEmailRequest);

        kafkaTemplate.send(confirmEmailTopic, new EmailMessage(email, "Код подтверждения", code).toJson());

        String token = jwtService.generateToken(user);

        return new JwtTokenDto(token);
    }

    @Override
    public UserDTO getUserFromToken(JwtTokenDto token) {
        String email = jwtService.getClaims(token.getToken()).getSubject();
        return userService.findByEmail(email);
    }

    @Override
    public JwtTokenDto confirmEmail(ConfirmEmailDto confirmEmailDto) {
        String email = confirmEmailDto.getEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserByEmailNotFoundException(email));
        ConfirmEmailRequest request = confirmEmailRequestRepository.findByEmailAndCode(
                email,
                confirmEmailDto.getCode()
        ).orElseThrow(() -> new ConfirmEmailCodeNotFoundException(email, confirmEmailDto.getCode()));

        user.setState(UserState.ACTIVE);
        user.update();

        userRepository.save(user);
        confirmEmailRequestRepository.delete(request);

        return new JwtTokenDto(jwtService.generateToken(user));
    }

    @Override
    public void sendCode(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new UserByEmailNotFoundException(email);
        }

        String code = ConfirmEmailCodeUtil.generateCode();
        while (confirmEmailRequestRepository.existsByEmailAndCode(email, code)) {
            code = ConfirmEmailCodeUtil.generateCode();
        }

        ConfirmEmailRequest request = new ConfirmEmailRequest(email, code);
        confirmEmailRequestRepository.save(request);

        kafkaTemplate.send(confirmEmailTopic, new EmailMessage(email, "Код подтверждения", code).toJson());
    }
}
