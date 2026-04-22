package com.example.Employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

/**
 * DTO для регистрации нового пользователя.
 */
@Data
@Schema(description = "Запрос на регистрацию")
public class SignupRequest {

    @NotBlank
    @Size(min = 3, max = 50, message = "Username от 3 до 50 символов")
    @Schema(example = "newuser")
    private String username;

    @NotBlank
    @Size(min = 6, max = 120, message = "Пароль минимум 6 символов")
    @Schema(example = "password123")
    private String password;

    @NotBlank
    @Email(message = "Некорректный email")
    @Schema(example = "newuser@company.ru")
    private String email;

    @NotBlank
    @Schema(example = "Иван")
    private String firstName;

    @NotBlank
    @Schema(example = "Иванов")
    private String lastName;

    /**
     * Роли при регистрации. Если не указаны — ставим ROLE_USER по умолчанию.
     * Только ADMIN может назначать роли при регистрации (проверим в сервисе).
     */
    @Schema(description = "Роли (опционально)", example = "[\"user\"]")
    private Set<String> roles;
}
