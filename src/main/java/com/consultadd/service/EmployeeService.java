package com.consultadd.service;

import com.consultadd.controller.EmployeeController;
import com.consultadd.model.Employee;
import com.consultadd.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getEmployees()
    {
        return employeeRepository.findAll();
    }



    public String saveEmployee(Employee employee)
    {
        if(employeeRepository.existsById(employee.getId()))
        {
            return "Couldn't save data Id Already Exist";
        }
        else {
            employeeRepository.save(employee);
            return "Employee data saved successfully";
        }

    }
    public String updateEmployee(Employee employee)
    {
        if(employeeRepository.existsById(employee.getId()))
        {
//            employeeRepository.delete(employee);
            employeeRepository.save(employee);
            return "Id updated";
        }
        else {

            return "Employee data does not exits";
        }

    }
    public String deleteEmployee(Employee employee)
    {
        if(employeeRepository.existsById(employee.getId()))
        {
            employeeRepository.delete(employee);

            return "Id deleted";
        }
        else {

            return "Employee data does not exits";
        }

    }



}
