package com.brogrammers.employee.mapper;

import com.brogrammers.employee.dto.EmployeeDTO;
import com.brogrammers.employee.entity.Employee;

public class EmployeeMapper {

    public static Employee mapToEntity(EmployeeDTO employee) {
        return Employee.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .department(employee.getDepartment())
                .build();
    }

    public static EmployeeDTO mapToDto(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .department(employee.getDepartment())
                .build();
    }
}
