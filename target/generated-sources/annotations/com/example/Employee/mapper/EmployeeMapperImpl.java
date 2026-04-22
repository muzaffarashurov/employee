package com.example.Employee.mapper;

import com.example.Employee.dto.EmployeeDto;
import com.example.Employee.entity.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-22T20:09:39+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Microsoft)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDto toDto(Employee entity) {
        if ( entity == null ) {
            return null;
        }

        EmployeeDto.EmployeeDtoBuilder employeeDto = EmployeeDto.builder();

        employeeDto.id( entity.getId() );
        employeeDto.firstName( entity.getFirstName() );
        employeeDto.lastName( entity.getLastName() );
        employeeDto.email( entity.getEmail() );
        employeeDto.position( entity.getPosition() );
        employeeDto.salary( entity.getSalary() );
        employeeDto.createdAt( entity.getCreatedAt() );
        employeeDto.updatedAt( entity.getUpdatedAt() );

        return employeeDto.build();
    }

    @Override
    public Employee toEntity(EmployeeDto dto) {
        if ( dto == null ) {
            return null;
        }

        Employee.EmployeeBuilder employee = Employee.builder();

        employee.id( dto.getId() );
        employee.firstName( dto.getFirstName() );
        employee.lastName( dto.getLastName() );
        employee.email( dto.getEmail() );
        employee.position( dto.getPosition() );
        employee.salary( dto.getSalary() );

        return employee.build();
    }

    @Override
    public void EntityFromDto(EmployeeDto dto, Employee entity) {
        if ( dto == null ) {
            return;
        }

        entity.setFirstName( dto.getFirstName() );
        entity.setLastName( dto.getLastName() );
        entity.setEmail( dto.getEmail() );
        entity.setPosition( dto.getPosition() );
        entity.setSalary( dto.getSalary() );
    }

    @Override
    public List<EmployeeDto> toDtoList(List<Employee> entities) {
        if ( entities == null ) {
            return null;
        }

        List<EmployeeDto> list = new ArrayList<EmployeeDto>( entities.size() );
        for ( Employee employee : entities ) {
            list.add( toDto( employee ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(EmployeeDto dto, Employee entity) {
        if ( dto == null ) {
            return;
        }

        entity.setFirstName( dto.getFirstName() );
        entity.setLastName( dto.getLastName() );
        entity.setEmail( dto.getEmail() );
        entity.setPosition( dto.getPosition() );
        entity.setSalary( dto.getSalary() );
    }
}
