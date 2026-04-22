package com.example.Employee.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * JPA Сущность (Entity) - класс, который мапится на таблицу в БД.
 * Каждое поле = колонка в таблице employees.
 * <p>
 * Аннотации Lombok:
 *
 * @Data - генерирует геттеры, сеттеры, toString, equals, hashCode
 * @NoArgsConstructor - конструктор без параметров (обязателен для JPA)
 * @AllArgsConstructor - конструктор со всеми полями
 * @Builder - паттерн Builder для удобного создания объектов
 * JPA Сущность — маппится на таблицу employees, созданную Liquibase YAML.
 *
 * ВАЖНО: Поля этой сущности ДОЛЖНЫ соответствовать колонкам в YAML-миграции!
 * Если добавишь поле в Entity — добавь колонку в новой миграции YAML!
 *
 * Соответствие:
 *   Entity field        -> YAML column
 *   ----------------    -> ---------------
 *   id                  -> id BIGINT AUTO_INCREMENT PK
 *   firstName           -> first_name VARCHAR(100) NOT NULL
 *   lastName            -> last_name VARCHAR(100) NOT NULL
 *   email               -> email VARCHAR(150) NOT NULL UNIQUE
 *   position            -> position VARCHAR(100) NULL
 *   salary              -> salary DECIMAL(10,2) NULL
 *   createdAt           -> created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
 *   updatedAt           -> updated_at TIMESTAMP (с триггером автообновления)
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees") //Имя таблицы в БД
@Entity
@Builder
public class Employee {
    /**
     * Первичный ключ.
     *
     * @Id - помечает поле как PK
     * @GeneratedValue - стратегия генерации ID:
     * IDENTITY - использует auto_increment MySQL (лучший вариант для MySQL)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Имя сотрудника.
     *
     * @Column(nullable = false) - поле не может быть NULL в БД
     */
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    /**
     * Фамилия сотрудника.
     */
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    /**
     * Email (уникальный).
     */
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    /**
     * Должность (может быть null).
     */
    @Column(length = 100)
    private String position;

    /**
     * Зарплата.
     * BigDecimal - для точных денежных расчетов (не используй float/double для денег!)
     */
    @Column(precision = 10, scale = 2)
    private BigDecimal salary;

    /**
     * Дата создания. Автоматически ставится при вставке.
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Дата обновления. Автоматически обновляется при изменении.
     */
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
