package com.example.Employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) - объект для передачи данных между слоями/по сети.
 * <p>
 * Зачем нужен DTO?
 * 1. Скрыть внутреннюю структуру Entity (не выставлять наружу поля createdAt/updatedAt)
 * 2. Валидация входящих данных
 * 3. Гибкость: можно объединять данные из нескольких Entity
 * 4. Защита от случайных изменений в Entity
 *
 * @Schema - аннотации Swagger для документации API
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO сотрудника")
public class EmployeeDto {
    /**
     * ID сотрудника. При создании (POST) не передается, при обновлении (PUT) - передается.
     */
    @Schema(description = "ID сотрудника", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Имя", example = "Иван", requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;

    @Schema(description = "Фамилия", example = "Иванов", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastName;

    @Schema(description = "Email", example = "ivan@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "Должность", example = "Java Developer")
    private String position;

    @Schema(description = "Зарплата", example = "150000.00")
    private BigDecimal salary;

    /**
     * Эти поля только для чтения (возвращаются в ответе, но не принимаются в запросе).
     * Можно вынести в отдельный ResponseDto, но для простоты оставим здесь.
     */
    @Schema(description = "Дата создания", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @Schema(description = "Дата обновления", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updatedAt;
}
