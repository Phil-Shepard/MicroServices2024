package org.example.userservice.models.entities;

import jakarta.persistence.*;
import org.example.models.enums.RecordState;
import org.example.models.enums.UserState;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

/**
 * @author Tribushko Danil
 * @since 08.12.2024
 * Сущность пользователя
 */
@Entity
@Table(name = "users")
public class User extends EntityVersion implements UserDetails {
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column
    private UserState state;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role"))
    private Set<Role> roles;

    public User(
            String email,
            String username,
            String password,
            UserState state,
            Set<Role> roles) {
        super();
        this.email = email;
        this.username = username;
        this.password = password;
        this.state = state;
        this.roles = roles;
    }

    public User() {
        super();
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
        private Set<Role> roles;

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

        public Builder roles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        public User build() {
            User user = new User();
            user.id = this.id;
            user.email = this.email;
            user.username = this.username;
            user.password = this.password;
            user.state = this.state;
            user.roles = this.roles;
            return user;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return state == UserState.ACTIVE || state == UserState.NON_CONFIRM;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        update();
    }

    public void setPassword(String password) {
        this.password = password;
        update();
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
        update();
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
        update();
    }

    public void setUsername(String username) {
        this.username = username;
        update();
    }
}
