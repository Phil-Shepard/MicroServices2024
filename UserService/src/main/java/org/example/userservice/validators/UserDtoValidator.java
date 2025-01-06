package org.example.userservice.validators;

import org.example.exceptions.api.ValidationException;
import org.example.models.dto.UserDTO;
import org.example.utils.RegexUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;

/**
 * @author Tribushko Danil
 * @since 31.12.2024
 * Валидатор dto пользователя
 */
@Component
public class UserDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO dto = (UserDTO) target;
        Matcher emailMatch = RegexUtils.matcher(RegexUtils.emailRegex(), dto.getEmail());

        if (dto.getEmail() != null && !emailMatch.find()) {
            throw new ValidationException("В поле \"email\", должен быть указан электронный адресс пользователя");
        }

        if (dto.getPassword() != null && dto.getPassword().length() < 8) {
            throw new ValidationException("Пароль должен содержать минимум 8 символов");
        }

        if (dto.getUsername() != null && dto.getUsername().length() < 8) {
            throw new ValidationException("Имя пользователя должно содержать минимум 8 символов");
        }
    }
}
