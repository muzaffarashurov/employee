package com.example.Employee;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Базовый тест: проверяет, что контекст Spring загружается без ошибок.
 * Для запуска: mvn test
 */
@SpringBootTest
class EmployeeApplicationTests {

    @Test
    void contextLoads() {
        // Если тест прошел - значит приложение стартует корректно
    }

}
