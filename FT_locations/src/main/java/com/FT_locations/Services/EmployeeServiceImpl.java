package com.FT_locations.Services;

import com.FT_locations.Models.Employee;
import com.FT_locations.Repositories.EmployeeRepo;
import com.FT_locations.Services.iServices.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    public List<Employee> getEmployees(){
        return employeeRepo.findAll();
    }

    public Employee getEmployee(int id){
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        return optionalEmployee.orElse(null);
    }
}
