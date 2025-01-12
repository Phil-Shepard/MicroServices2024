package org.example.taskservice.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.exceptions.api.UserNotAdminException;
import org.example.models.dto.RoleDTO;
import org.example.models.dto.UserDTO;
import org.example.taskservice.models.dto.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Aspect
public class UsersAspect {
    @Before(value = "@annotation(org.example.taskservice.aspects.annotations.IsAdmin)")
    public void checkUserAdmin() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication != null) {
            SecurityUser user = (SecurityUser) authentication;
            for (RoleDTO role : user.getUser().getRoles()) {
                if (role.getName().equals("ROLE_ADMIN")) {
                    throw new UserNotAdminException(user.getUsername());
                }
            }
        }
    }
}
