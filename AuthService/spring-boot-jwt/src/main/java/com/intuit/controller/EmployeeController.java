package com.intuit.controller;

import com.intuit.model.Employee;
import com.intuit.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    @PostMapping("/employee/create")
    private String createAccessRequest(@RequestBody List<Employee> employeeList)
    {
        employeeRepository.saveAll(employeeList);
        return "success";
    }

}
