package com.example.Employee.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Сущность "Роль" — определяет права доступа в системе.
 * <p>
 * В Spring Security роли обычно именуются с префиксом ROLE_.
 * Это важно! Spring Security по умолчанию ожидает этот префикс
 * при проверке hasRole("ADMIN") → ищет "ROLE_ADMIN".
 */
@Entity
@Table(name = "roles")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название роли. Уникальное, не null.
     * Примеры: ROLE_ADMIN, ROLE_MANAGER, ROLE_USER
     */
    @Column(nullable = false, unique = true, length = 50)
    private String name;
}
