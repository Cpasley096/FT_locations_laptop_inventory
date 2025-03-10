package com.FT_locations.Services.iServices;

import com.FT_locations.Models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();
    Employee getEmployee(int id);
}
