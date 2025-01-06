package org.example.userservice.models.mappers;

import org.example.models.dto.RoleDTO;
import org.example.models.dto.UserDTO;
import org.example.userservice.models.entities.Role;
import org.example.userservice.models.entities.User;

import java.util.HashSet;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        return new UserDTO()
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .creationDate(user.getCreationDate())
                .updateDate(user.getUpdateDate())
                .roles(user.getRoles()
                        .stream()
                        .map(role -> new RoleDTO(role.getName()))
                        .collect(Collectors.toSet()))
                .state(user.getState())
                .build();
    }
}
