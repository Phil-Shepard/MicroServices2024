package org.example.exceptions.api;

/**
 * @author Tribushko Danil
 * @since 12.01.2025
 */
public class UserNotAdminException extends BaseApiException {
    public UserNotAdminException(String username) {
        super(403, "Пользователь: " + username + " не является администатоом");
    }
}
