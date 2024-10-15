package com.brogrammers.employee.mapper;

import com.brogrammers.employee.dto.EmployeeDTO;
import com.brogrammers.employee.entity.Employee;

public class EmployeeMapper {

    public static Employee mapToEntity(EmployeeDTO employee) {
        return Employee.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
    }

    public static EmployeeDTO mapToDto(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
    }
}
