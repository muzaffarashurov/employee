package com.brogrammers.employee.service;

import com.brogrammers.employee.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO getEmployeeById(Long id);

    List<EmployeeDTO> getAllEmployees();


}
