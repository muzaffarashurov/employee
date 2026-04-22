package com.example.Employee.repository;

import com.example.Employee.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с ролями.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Найти роль по точному названию.
     * Используем при регистрации, когда пользователь указывает роли.
     */
    Optional<Role> findByName(String name);
}
