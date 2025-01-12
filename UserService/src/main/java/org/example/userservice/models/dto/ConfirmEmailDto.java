package org.example.userservice.models.dto;

public class ConfirmEmailDto {
    private String email;
    private String code;

    public ConfirmEmailDto(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public String getCode() {
        return code;
    }
}
