package com.gini.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.gini.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

    private final DynamoDBMapper dynamoDBMapper;


    public Employee save(Employee employee){
        dynamoDBMapper.save(employee);
        return employee;
    }

    public Employee getEmployeeById(String employeeId){
        return dynamoDBMapper.load(Employee.class, employeeId);
    }

    public String delete (String employeeId){
        Employee emp = dynamoDBMapper.load(Employee.class, employeeId);
        dynamoDBMapper.delete(emp);
        return "Employee deleted";
    }

    public String update(String employeeId, Employee employee){
        dynamoDBMapper.save(employee, new DynamoDBSaveExpression()
                .withExpectedEntry("employeeId",
                        new ExpectedAttributeValue(
                                new AttributeValue().withS(employeeId)
                        )
                )
        );
        return employeeId;
    }


}
