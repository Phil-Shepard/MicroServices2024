package org.example.userservice.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * @author Tribusko Danil
 * @since 02.01.2024
 * Сущность подтверждения электронного адреса
 */
@Entity
@Table(name = "confirm_email_request", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "code"}))
public class ConfirmEmailRequest extends EntityCreationDate {
    @Column(nullable = false, name = "email")
    private String email;
    @Column(nullable = false, name = "code")
    private String code;

    public ConfirmEmailRequest(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public ConfirmEmailRequest() {}

    public String getEmail() {
        return email;
    }

    public String getCode() {
        return code;
    }
}
