package com.example.Employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO для запроса аутентификации (логин).
 *
 * @NotBlank — валидация: поле не может быть null, пустым или только пробелами.
 * Это работает благодаря spring-boot-starter-validation.
 */
@Data
@Schema(description = "Запрос на аутентификацию")
public class LoginRequest {

    @NotBlank(message = "Username обязателен")
    @Schema(description = "Логин", example = "admin", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @NotBlank(message = "Password обязателен")
    @Schema(description = "Пароль", example = "admin123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
