package com.example.Employee.service;

import com.example.Employee.dto.EmployeeDto;

import java.util.List;

/**
 * Service Interface - контракт бизнес-логики.
 * <p>
 * Почему интерфейс + реализация (ServiceImpl)?
 * 1. Инверсия зависимостей (Dependency Inversion) - зависим от абстракции
 * 2. Легко заменить реализацию (например, моки для тестов)
 * 3. Spring использует JDK Dynamic Proxy для транзакций (требует интерфейс)
 */

public interface EmployeeService {

    /**
     * Создать нового сотрудника.
     *
     * @param dto данные сотрудника
     * @return созданный сотрудник с ID
     */
    EmployeeDto create(EmployeeDto dto);

    /**
     * Получить сотрудника по ID.
     *
     * @param id идентификатор
     * @return DTO сотрудника
     */
    EmployeeDto getById(Long id);

    /**
     * Получить всех сотрудников.
     */
    List<EmployeeDto> getAll();

    /**
     * Обновить данные сотрудника.
     *
     * @param id  идентификатор
     * @param dto новые данные
     * @return обновленный сотрудник
     */
    EmployeeDto update(Long id, EmployeeDto dto);

    /**
     * Удалить сотрудника.
     *
     * @param id идентификатор
     */
    void delete(Long id);
}
