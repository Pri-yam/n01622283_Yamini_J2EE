package com.demo.empMgmtSys.service;

import com.demo.empMgmtSys.model.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee); // Create or Update
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id);
}
