package com.example.Employee.mapper;

import com.example.Employee.dto.EmployeeDto;
import com.example.Employee.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * MapStruct Mapper - автоматически генерирует код преобразования DTO <-> Entity.
 * <p>
 * componentModel = "spring" - маппер будет Spring-бином (можно внедрять через @Autowired)
 * <p>
 * НЕ НУЖНО писать реализацию! MapStruct сгенерирует EmployeeMapperImpl автоматически
 * при компиляции (mvn clean compile).
 * <p>
 * Генерированный код будет в: target/generated-sources/annotations/...
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    /**
     * Преобразовать Entity в DTO.
     * Поля с одинаковыми именами копируются автоматически.
     */
    EmployeeDto toDto(Employee entity);

    /**
     * Преобразовать DTO в Entity.
     * Игнорируем поля аудита (createdAt, updatedAt) - они управляются БД/Hibernate.
     */
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Employee toEntity(EmployeeDto dto);

    /**
     * Обновить существующую Entity данными из DTO.
     *
     * @MappingTarget - указывает, какой объект обновлять.
     * Игнорируем id и поля аудита, чтобы случайно не изменить их.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void EntityFromDto(EmployeeDto dto, @MappingTarget Employee entity);

    /**
     * Преобразовать список Entity в список DTO.
     * MapStruct автоматически применит toDto() для каждого элемента.
     */
    List<EmployeeDto> toDtoList(List<Employee> entities);

    /**
     * Обновить существующую Entity данными из DTO.
     *
     * @MappingTarget - указывает, какой объект обновлять.
     * Игнорируем id и поля аудита, чтобы случайно не изменить их.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(EmployeeDto dto, @MappingTarget Employee entity);

}
