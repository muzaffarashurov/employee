package com.example.Employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * DTO с JWT токеном — возвращается после успешного логина.
 * <p>
 * Клиент должен сохранить этот токен и отправлять в заголовке:
 * Authorization: Bearer <token>
 */
@Data
@AllArgsConstructor
@Schema(description = "Ответ с JWT токеном")
public class JwtResponse {

    @Schema(description = "Тип токена", example = "Bearer")
    private String type;

    @Schema(description = "JWT токен доступа")
    private String accessToken;

    @Schema(description = "ID пользователя")
    private Long id;

    @Schema(description = "Логин")
    private String username;

    @Schema(description = "Email")
    private String email;

    @Schema(description = "Список ролей")
    private List<String> roles;
}
