package com.consultadd.controller;
import java.util.List;
import com.consultadd.model.Employee;
import com.consultadd.repository.EmployeeRepository;
import com.consultadd.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> getEmployees()
    {
       return employeeService.getEmployees();

    }
    @PostMapping("/addemp")
    public String saveEmployee(Employee employee)
    {
        return employeeService.saveEmployee(employee);
    }
    @PutMapping("/updateemp")
    public String updateEmployee(Employee employee)
    {
        return employeeService.updateEmployee(employee);
    }
    @DeleteMapping("/deleteemp")
    public String deleteEmployee(Employee employee)
    {
        return employeeService.deleteEmployee(employee);
    }

}
