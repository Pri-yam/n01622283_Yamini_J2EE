package com.example.lab3_empmgmtsystem.service;

import com.example.lab3_empmgmtsystem.model.Employee;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class EmpAppService {
    private static Map<Integer, Employee> employeeMap = new HashMap<>();
    private static int currentId = 1;

    public List<Employee> getAll() {
        return new ArrayList<>(employeeMap.values());
    }

    public Employee getById(int id) {
        return employeeMap.get(id);
    }

    public Employee create(Employee emp) {
        emp.setId(currentId++);
        employeeMap.put(emp.getId(), emp);
        return emp;
    }

    public Employee update(int id, Employee emp) {
        if (!employeeMap.containsKey(id)) return null;
        emp.setId(id);
        employeeMap.put(id, emp);
        return emp;
    }

    public boolean delete(int id) {
        return employeeMap.remove(id) != null;
    }
}
