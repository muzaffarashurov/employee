package com.example.Employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс Spring Boot приложения.
 * Аннотация @SpringBootApplication объединяет:
 * - @Configuration - помечает класс как источник бинов
 * - @EnableAutoConfiguration - автоматическая настройка Spring
 * - @ComponentScan - сканирование компонентов в текущем пакете и подпакетах
 * <p>
 * Запуск: java -jar target/employee-1.0.0.jar
 * Или через IDE: Run -> EmployeeApplication
 */
@SpringBootApplication
public class EmployeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }

}
