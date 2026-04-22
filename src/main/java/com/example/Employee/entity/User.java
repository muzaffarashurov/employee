package com.example.Employee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность "Пользователь" — учётная запись для входа в систему.
 * <p>
 * Отдельно от Employee, потому что:
 * 1. Не все сотрудники имеют доступ в систему
 * 2. Один человек может иметь несколько аккаунтов
 * 3. Security и бизнес-данные — разные домены
 */
@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false)
    private Boolean enabled = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Связь Many-to-Many с ролями.
     * <p>
     * FetchType.EAGER — загружаем роли сразу при загрузке User.
     * Это нужно для Spring Security, который сразу проверяет права.
     * <p>
     * В продакшене лучше EAGER → LAZY + @EntityGraph, но для учебы оставим EAGER.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles", // Таблица связи
            joinColumns = @JoinColumn(name = "user_id"), // Колонка на эту сущность
            inverseJoinColumns = @JoinColumn(name = "role_id") // Колонка на Role
    )
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    /**
     * Автоматически ставим время создания перед сохранением.
     * Аналог @CreationTimestamp, но без зависимости от Hibernate.
     */
    @PrePersist
    protected void onCreatd() {
        createdAt = LocalDateTime.now();
    }
}
