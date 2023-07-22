package com.example.hr_management.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.hr_management.entity.Dependent;
import com.example.hr_management.entity.Employee;
import com.example.hr_management.exception.EmployeeNotFoundException;
import com.example.hr_management.repository.DependentRepository;
import com.example.hr_management.repository.EmployeeRepository;
import com.example.hr_management.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  private EmployeeRepository employeeRepository;
  private DependentRepository dependentRepository;

  public EmployeeServiceImpl(EmployeeRepository employeeRepository, DependentRepository dependentRepository) {
    this.employeeRepository = employeeRepository;
    this.dependentRepository = dependentRepository;
  }

  @Override
  public Employee createEmployee(Employee employee) {
    Employee newEmployee = employeeRepository.save(employee);
    return newEmployee;
  }

  @Override
  public Employee getEmployee(int id) {
    Optional<Employee> wrappedFoundEmployee = employeeRepository.findById(id);

    if (!wrappedFoundEmployee.isPresent()) {
      throw new EmployeeNotFoundException(id);
    }

    Employee foundEmployee = wrappedFoundEmployee.get();
    return foundEmployee;
  }

  @Override
  public List<Employee> getAllEmployees() {
    List<Employee> allEmployees = employeeRepository.findAll();
    return allEmployees;
  }

  @Override
  public Employee updateEmployee(int id, Employee employee) {
    Optional<Employee> wrappedEmployeeToUpdate = employeeRepository.findById(id);

    if (!wrappedEmployeeToUpdate.isPresent()) {
      throw new EmployeeNotFoundException(id);
    }

    Employee employeeToUpdate = wrappedEmployeeToUpdate.get();
    employeeToUpdate.setFirstName(employee.getFirstName());
    employeeToUpdate.setLastName(employee.getLastName());
    employeeToUpdate.setEmail(employee.getEmail());
    employeeToUpdate.setPhoneNumber(employee.getPhoneNumber());
    employeeToUpdate.setHireDate(employee.getHireDate());
    return employeeRepository.save(employeeToUpdate);
  }

  @Override
  public void deleteEmployee(int id) {
    employeeRepository.deleteById(id);
  }

  @Override
  public Dependent addDependentToEmployee(int id, Dependent dependent) {
    // Retrieve the Employee from the DB
    Optional<Employee> wrappedSelectedEmployee = employeeRepository.findById(id);

    if (!wrappedSelectedEmployee.isPresent()) {
      throw new EmployeeNotFoundException(id);
    }

    Employee selectedEmployee = wrappedSelectedEmployee.get();

    // Add the employee to the dependent
    dependent.setEmployee(selectedEmployee);
    // Save the dependent to the DB
    return dependentRepository.save(dependent);
  }
}
