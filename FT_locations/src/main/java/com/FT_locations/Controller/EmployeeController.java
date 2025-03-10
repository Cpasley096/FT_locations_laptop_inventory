package com.FT_locations.Controller;

import com.FT_locations.Models.Employee;
import com.FT_locations.Services.iServices.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class EmployeeController {
    @Autowired
    private EmployeeService EmployeeService;
    @GetMapping({"/", ""})
    List<Employee> listUsers() {
        return EmployeeService.getEmployees();
    }
}