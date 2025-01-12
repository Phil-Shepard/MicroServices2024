package org.example.userservice.services.imp;

import org.example.models.dto.UserDTO;
import org.example.models.enums.UserState;
import org.example.userservice.models.entities.Role;
import org.example.userservice.models.entities.User;
import org.example.userservice.models.exceptions.*;
import org.example.userservice.models.mappers.UserMapper;
import org.example.userservice.repositories.UserPaginationRepository;
import org.example.userservice.repositories.UserRepository;
import org.example.userservice.services.RoleService;
import org.example.userservice.services.UserService;
import org.example.userservice.validators.UserDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.List;

/**
 * @author Tribushko Danil
 * @since 01.01.2025
 */
@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final UserPaginationRepository userPaginationRepository;
    private final UserDtoValidator userDtoValidator;
    private final RoleService roleService;

    @Autowired
    public UserServiceImp(
            UserRepository userRepository,
            UserDtoValidator userDtoValidator,
            UserPaginationRepository userPaginationRepository,
            RoleService roleService
    ) {
        this.userRepository = userRepository;
        this.userDtoValidator = userDtoValidator;
        this.userPaginationRepository = userPaginationRepository;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserByUsernameNotFoundException(username));
    }

    @Override
    public UserDTO findByEmail(String email) {
        return UserMapper.toDTO(userRepository.findByEmail(email)
                .orElseThrow(() -> new UserByEmailNotFoundException(email)));
    }

    @Override
    public List<UserDTO> findAll(Integer page, Integer perPage, String role) {
        return userPaginationRepository.findAllByRole(
                        PageRequest.of(page, perPage),
                        roleService.getByName(role)
                )
                .map(UserMapper::toDTO)
                .toList();
    }

    @Override
    public UserDTO findById(Long id) {
        return UserMapper.toDTO(findEntityById(id));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.delete(findEntityById(id));
    }

    @Override
    public UserDTO update(UserDTO entity) {
        Errors errors = new BeanPropertyBindingResult(entity, "user");
        userDtoValidator.validate(entity, errors);
        User user = userRepository.findById(entity.getId()).orElseThrow(
                () -> new UserByIdNotFoundException(entity.getId())
        );

        boolean isUpdated = false;

        String email = entity.getEmail();
        if (email != null) {
            if (userRepository.existsByEmail(email)) {
                throw new UserByEmailAlreadyExistException(email);
            } else {
                user.setEmail(email);
                user.setState(UserState.NON_CONFIRM);
                isUpdated = true;
            }
        }

        String password = entity.getPassword();
        if (password != null) {
            user.setPassword(password);
            isUpdated = true;
        }

        String username = entity.getUsername();
        if (username != null) {
            if (userRepository.existsByUsername(username)) {
                throw new UserByUsernameAlreadyExistException(username);
            } else {
                user.setUsername(username);
                isUpdated = true;
            }
        }

        if (isUpdated) {
            user.update();
        }

        User updatedUser = userRepository.save(user);

        return UserMapper.toDTO(updatedUser);
    }

    private User findEntityById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserByIdNotFoundException(id));
    }
}
