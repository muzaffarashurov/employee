package com.example.Employee.repository;

import com.example.Employee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с пользователями.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Найти пользователя по логину.
     * Используется при аутентификации (Spring Security ищет пользователя).
     */
    Optional<User> findByUsername(String username);

    /**
     * Проверить существование логина.
     * Нужно при регистрации — нельзя создать дубликат.
     */
    boolean existsByUsername(String username);

    /**
     * Проверить существование email.
     */
    boolean existsByEmail(String email);
}
