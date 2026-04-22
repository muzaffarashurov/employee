package com.example.Employee.repository;

import com.example.Employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository (DAO) - слой доступа к данным.
 *
 * JpaRepository<Entity, ID> дает нам готовые методы:
 * - save(entity) - сохранить/обновить
 * - findById(id) - найти по ID
 * - findAll() - найти все
 * - deleteById(id) - удалить по ID
 * - existsById(id) - проверить существование
 * - count() - количество записей
 *
 * Spring Data JPA автоматически создает реализацию (прокси) во время выполнения.
 * Не нужно писать имплементацию!
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Проверка существования email.
     */
    boolean existsByEmail(String email);
}
