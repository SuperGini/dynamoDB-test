package com.gini.controller;

import com.gini.entity.v2.Employee;
import com.gini.repository.EmployeeRepository;
import com.gini.repository.v2.EmployeeRepository2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final EmployeeRepository2  employeeRepository2;

    @PostMapping("/employee")
    public com.gini.entity.v2.Employee saveEmployee (@RequestBody com.gini.entity.v2.Employee employee){
        return employeeRepository2.save(employee);
    }

    @GetMapping("/employee/{employeeId}")
    public Employee getEmployee(@PathVariable String employeeId){

        return employeeRepository2.getEmployee(employeeId);

      // return employeeRepository.getEmployeeById(employeeId);
    }


    @DeleteMapping("/employee/{employeeId}")
    public Employee deleteEmployee(@PathVariable String employeeId){
        return employeeRepository2.deleteItem(employeeId);
    }

}
