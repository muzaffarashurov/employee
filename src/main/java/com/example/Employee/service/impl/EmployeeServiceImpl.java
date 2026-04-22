package com.example.Employee.service.impl;

import com.example.Employee.dto.EmployeeDto;
import com.example.Employee.entity.Employee;
import com.example.Employee.mapper.EmployeeMapper;
import com.example.Employee.repository.EmployeeRepository;
import com.example.Employee.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Реализация сервиса сотрудников.
 *
 * @Service - помечает класс как Spring-бин (бизнес-логика)
 * @RequiredArgsConstructor - генерирует конструктор с final полями (внедрение зависимостей)
 * @Slf4j - логгер (log.info(), log.error() и т.д.)
 * @Transactional - все методы выполняются в транзакции (rollback при ошибке)
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)// По умолчанию: только чтение (оптимизация)
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * final + @RequiredArgsConstructor = автоматическое внедрение через конструктор.
     * Это лучше чем @Autowired над полем (тестируемость, immutability).
     */
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    /**
     * Создание сотрудника.
     *
     * @Transactional - метод изменяет данные (readOnly = false по умолчанию)
     */

    @Override
    @Transactional
    public EmployeeDto create(EmployeeDto dto) {
        log.info("Создание нового сотрудника: {} {}", dto.getFirstName(), dto.getLastName());
        // Проверка уникальности email
        if (employeeRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Сотрудник с email " + dto.getEmail() + " уже существует");
        }
        // Преобразуем DTO -> Entity
        Employee entity = employeeMapper.toEntity(dto);
        // Сохраняем в БД (JPA присвоит ID)
        Employee saved = employeeRepository.save(entity);
        log.info("Сотрудник создан с ID: {}", saved.getId());
        // Преобразуем обратно Entity -> DTO и возвращаем
        return employeeMapper.toDto(saved);
    }

    /**
     * Получение по ID.
     * orElseThrow - если не найден, выбрасываем исключение (вернет 404 через ControllerAdvice)
     */
    @Override
    public EmployeeDto getById(Long id) {
        log.info("Поиск сотрудника по ID: {}", id);
        Employee entity = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Сотрудник с ID " + id + " не найден"));
        return employeeMapper.toDto(entity);
    }

    /**
     * Получение всех сотрудников.
     */
    @Override
    public List<EmployeeDto> getAll() {
        log.info("Получение списка всех сотрудников");
        // findAll() возвращает List<Employee>, маппер конвертирует в List<EmployeeDto>
        return employeeMapper.toDtoList(employeeRepository.findAll());
    }

    /**
     * Обновление сотрудника.
     */
    @Override
    @Transactional
    public EmployeeDto update(Long id, EmployeeDto dto) {
        log.info("Обновление сотрудника с ID: {}", id);

        // Находим существующую запись
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Сотрудник с ID " + id + " не найден"));

        // Проверяем, что новый email не занят другим сотрудником
        if (!existing.getEmail().equals(dto.getEmail()) && employeeRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email " + dto.getEmail() + " уже используется");
        }

        // Обновляем поля существующей Entity (через маппер)
        // Важно: обновляем существующий объект, а не создаем новый - сохраняет ID и createdAt
        employeeMapper.updateEntityFromDto(dto, existing);

        // save() с существующим ID делает UPDATE вместо INSERT
        Employee updated = employeeRepository.save(existing);
        log.info("Сотрудник с ID {} обновлен", id);

        return employeeMapper.toDto(updated);
    }

    /**
     * Удаление сотрудника.
     */
    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Удаление сотрудника с ID: {}", id);

        // Проверяем существование перед удалением
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Сотрудник с ID " + id + " не найден");
        }
        employeeRepository.deleteById(id);
        log.info("Сотрудник с ID {} удален", id);
    }
}
