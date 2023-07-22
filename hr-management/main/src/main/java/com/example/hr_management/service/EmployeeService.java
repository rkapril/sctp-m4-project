package com.example.hr_management.service;

import java.util.List;

import com.example.hr_management.entity.Dependent;
import com.example.hr_management.entity.Employee;

public interface EmployeeService {
  Employee createEmployee(Employee employee);

  Employee getEmployee(int id);

  List<Employee> getAllEmployees();

  Employee updateEmployee(int id, Employee employee);

  void deleteEmployee(int id);

  Dependent addDependentToEmployee(int id, Dependent dependent);
}
