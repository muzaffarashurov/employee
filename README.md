# Employee CRUD App

Простое Spring Boot приложение для управления сотрудниками (CRUD). Реализована работа с базой данных MySQL, миграции через Liquibase, маппинг через MapStruct, документация API через Swagger/OpenAPI.

## Технологии

- Java 17
- Spring Boot 3.5.3
- Spring MVC, Spring Data JPA
- MySQL
- Liquibase
- Lombok
- MapStruct
- SpringDoc OpenAPI (Swagger)
- Maven

## Требования

- JDK 17
- MySQL (локально или удалённо)
- Maven (или использование встроенной обёртки)

## Настройка и запуск

### 1. База данных

Убедитесь, что MySQL запущен. Приложение автоматически создаст базу данных `employee_db`, если её нет (параметр `createDatabaseIfNotExist=true`).

Настройки подключения (по умолчанию):
```yaml
url: jdbc:mysql://localhost:3306/employee_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
username: root
password: root
```

При необходимости измените логин/пароль в `src/main/resources/application.yaml`.

### 2. Сборка и запуск

```bash
# Собрать проект
mvn clean package

# Запустить
java -jar target/employee-1.0.0.jar
```

Или через Maven:
```bash
mvn spring-boot:run
```

### 3. Liquibase миграции

При первом запуске Liquibase создаст таблицу `employees`, индексы, триггер `updated_at` и добавит тестовые данные (5 записей). Тестовые данные добавляются только в контексте `dev` (см. `application.yaml`).

## Структура проекта

```
src/main/java/com/example/employee/
├── EmployeeApplication.java          # Точка входа
├── config/OpenApiConfig.java         # Настройка Swagger
├── controller/EmployeeController.java # REST контроллер
├── dto/EmployeeDto.java               # DTO
├── entity/Employee.java               # Сущность JPA
├── mapper/EmployeeMapper.java         # MapStruct маппер
├── repository/EmployeeRepository.java # JPA репозиторий
├── service/EmployeeService.java       # Интерфейс сервиса
├── service/impl/EmployeeServiceImpl.java # Реализация сервиса
└── exception/GlobalExceptionHandler.java # Обработка ошибок

src/main/resources/
├── application.yaml                   # Конфигурация Spring
└── db/changelog/
    ├── db.changelog-master.yaml       # Главный файл миграций
    └── changes/
        ├── 001-create-employee-table.yaml # Создание таблицы, индекса, триггера
        └── 002-insert-test-data.yaml      # Тестовые данные
```

## API Endpoints

Базовый путь: `/employees`

| Метод | URL             | Описание                |
|-------|-----------------|-------------------------|
| POST  | `/employees`    | Создать сотрудника      |
| GET   | `/employees/{id}`| Получить по ID          |
| GET   | `/employees`    | Получить всех           |
| PUT   | `/employees/{id}`| Обновить сотрудника     |
| DELETE| `/employees/{id}`| Удалить сотрудника      |

### Swagger UI

После запуска документация доступна по адресу:  
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

JSON спецификация OpenAPI: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## Примеры запросов (curl)

### Получить всех сотрудников
```bash
curl http://localhost:8080/employees
```

### Создать сотрудника
```bash
curl -X POST http://localhost:8080/employees \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Тест","lastName":"Тестов","email":"test@test.com","position":"Tester","salary":50000}'
```

### Получить по ID
```bash
curl http://localhost:8080/employees/1
```

### Обновить сотрудника
```bash
curl -X PUT http://localhost:8080/employees/1 \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Тест","lastName":"Обновленный","email":"test@test.com","position":"Senior Tester","salary":75000}'
```

### Удалить сотрудника
```bash
curl -X DELETE http://localhost:8080/employees/1
```

## Особенности реализации

- **Email** уникален, проверка при создании/обновлении.
- Поля `createdAt` и `updatedAt` заполняются автоматически (Hibernate + триггер MySQL для `updated_at`).
- Маппинг Entity ↔ DTO через MapStruct, исключая автоматическое обновление служебных полей.
- Глобальный обработчик исключений возвращает стандартизированный JSON с ошибкой.
- Логирование через `@Slf4j`.

## Возможные улучшения

- Добавить пагинацию для `GET /employees`.
- Валидацию входных данных (Bean Validation).
- Более сложные поисковые фильтры.
- Интеграционные тесты.
- Docker Compose для быстрого развёртывания с MySQL.

## Лицензия

Проект создан в учебных целях.
