package org.example.userservice.services.imp;

import org.example.userservice.models.entities.Role;
import org.example.userservice.models.exceptions.RoleByNameNotFoundException;
import org.example.userservice.repositories.RoleRepository;
import org.example.userservice.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tribushko Danil
 * @since 02.01.2025
 */
@Service
public class RoleServiceImp implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getUserRole() {
        return roleRepository
                .findByName("USER")
                .orElseThrow(() -> new RoleByNameNotFoundException("USER"));
    }

    @Override
    public Role getAdminRole() {
        return roleRepository
                .findByName("ADMIN")
                .orElseThrow(() -> new RoleByNameNotFoundException("ADMIN"));
    }
}
