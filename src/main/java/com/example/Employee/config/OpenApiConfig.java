package com.example.Employee.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация Swagger/OpenAPI.
 * Определяет заголовок и описание документации API.
 */
@Configuration
public class OpenApiConfig {

    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee API")
                        .version("1.0.0")
                        .description("Простое CRUD API для изучения Spring Boot")
                        .contact(new Contact()
                                .name("Developer")
                                .email("muzaffarashurov1982@gmail.com")));
    }
}
