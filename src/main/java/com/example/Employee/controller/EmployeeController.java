package com.example.Employee.controller;

import com.example.Employee.dto.EmployeeDto;
import com.example.Employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller - точка входа для HTTP запросов.
 *
 * @RestController = @Controller + @ResponseBody (автоматическая сериализация в JSON)
 * @RequestMapping("/employees") - базовый путь для всех методов
 * @RequiredArgsConstructor - внедрение сервиса через конструктор
 * @Tag - название группы в Swagger UI
 */
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Tag(name = "Employees", description = "CRUD операции с сотрудниками")
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * CREATE - Создание сотрудника.
     *
     * @PostMapping - обрабатывает HTTP POST запросы
     * @RequestBody - тело запроса автоматически конвертируется из JSON в EmployeeDto
     * ResponseEntity - обертка ответа (тело + статус код)
     */
    @PostMapping
    @Operation(summary = "Создать сотрудника", description = "Создает нового сотрудника в системе")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Сотрудник успешно создан"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные или дублирующийся email")
    })
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto dto) {
        EmployeeDto created = employeeService.create(dto);
        // Возвращаем 201 Created + тело ответа
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * READ ONE - Получение по ID.
     *
     * @GetMapping("/{id}") - GET /api/employees/123
     * @PathVariable - извлекает значение из URL
     */
    @GetMapping("/{id}")
    @Operation(summary = "Получить сотрудника по ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Сотрудник найден"),
            @ApiResponse(responseCode = "404", description = "Сотрудник не найден")
    })
    public ResponseEntity<EmployeeDto> getById(
            @Parameter(description = "ID сотрудника", example = "1")
            @PathVariable Long id) {
        EmployeeDto dto = employeeService.getById(id);
        return ResponseEntity.ok(dto); // 200 OK
    }

    /**
     * READ ALL - Получение списка.
     *
     * @GetMapping без параметров - GET /api/employees
     */
    @GetMapping
    @Operation(summary = "Получить всех сотрудников")
    public ResponseEntity<List<EmployeeDto>> getAll() {
        List<EmployeeDto> list = employeeService.getAll();
        return ResponseEntity.ok(list);
    }

    /**
     * UPDATE - Обновление.
     *
     * @PutMapping("/{id}") - полное обновление ресурса (все поля)
     * Для частичного обновления используй @PatchMapping
     */
    @Operation(summary = "Обновить сотрудника")
    @PutMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Сотрудник обновлен"),
            @ApiResponse(responseCode = "404", description = "Сотрудник не найден")
    })
    public ResponseEntity<EmployeeDto> update(
            @Parameter(description = "ID сотрудника", example = "1")
            @PathVariable Long id,
            @RequestBody EmployeeDto dto) {
        EmployeeDto updated = employeeService.update(id, dto);
        return ResponseEntity.ok(updated);

    }

    /**
     * DELETE - Удаление.
     *
     * @DeleteMapping("/{id}") - DELETE /api/employees/123
     * ResponseEntity.noContent() - 204 No Content (стандарт для DELETE)
     */
    @Operation(summary = "Удалить сотрудника")
    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Сотрудник удален"),
            @ApiResponse(responseCode = "404", description = "Сотрудник не найден")
    })
    public ResponseEntity<Void> delete(@Parameter(description = "ID сотрудника", example = "1")
                                       @PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
