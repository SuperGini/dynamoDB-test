package com.gini.repository.v2;


import com.gini.entity.v2.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository2 {

    private final DynamoDbTable<Employee> employeeTable;


    public Employee save(Employee employee){
        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeTable.putItem(employee);
        return employee;
    }

    public Employee getEmployee(String employeeId){
        Key key = Key.builder()
                .partitionValue(employeeId)
                .build();

       return employeeTable.getItem(key);
    }

    public Employee deleteItem(String employeeId){
        Key key = Key.builder()
                .partitionValue(employeeId)
                .build();

        return employeeTable.deleteItem(key);
    }


}
