package org.example.models.dto;

import org.example.models.enums.UserState;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Tribushko Danil
 * @since 08.12.2024
 * Dto пользователя
 */
public class UserDTO extends EntityVersionDTO {
    private String email;
    private String username;
    private String password;
    private UserState state;
    private Set<RoleDTO> roles;

    public UserDTO() {
        super(null, null, null);
    }

    public Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String email;
        private String username;
        private String password;
        private UserState state;
        private Set<RoleDTO> roles;
        private LocalDateTime creationDate;
        private LocalDateTime updateDate;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder state(UserState state) {
            this.state = state;
            return this;
        }

        public Builder roles(Set<RoleDTO> roles) {
            this.roles = roles;
            return this;
        }

        public Builder creationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Builder updateDate(LocalDateTime updateDate) {
            this.updateDate = updateDate;
            return this;
        }

        public UserDTO build() {
            UserDTO user = new UserDTO();
            user.id = this.id;
            user.email = this.email;
            user.username = this.username;
            user.password = this.password;
            user.state = this.state;
            user.roles = this.roles;
            user.creationDate = this.creationDate;
            user.updateDate = this.updateDate;
            return user;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }
}
