package com.brogrammers.employee.service;

import com.brogrammers.employee.dto.EmployeeDTO;
import com.brogrammers.employee.entity.Employee;
import com.brogrammers.employee.mapper.EmployeeMapper;
import com.brogrammers.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;


    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find employee'" + id));
        return EmployeeMapper.mapToDto(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::mapToDto).toList();
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employee) {
        Employee newEmployee = employeeRepository.save(EmployeeMapper.mapToEntity(employee));
        return EmployeeMapper.mapToDto(newEmployee);
    }
}
