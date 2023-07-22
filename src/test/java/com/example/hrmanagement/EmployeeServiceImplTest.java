package com.example.hrmanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.hr_management.HrManagementApplication;
import com.example.hr_management.entity.Employee;
import com.example.hr_management.repository.EmployeeRepository;
import com.example.hr_management.service.serviceImpl.EmployeeServiceImpl;

@SpringBootTest(classes = HrManagementApplication.class)
public class EmployeeServiceImplTest {

  // Create a mock of the EmployeeRepository
  @Mock
  private EmployeeRepository employeeRepository;

  // Inject the mocks as dependencies
  @InjectMocks
  EmployeeServiceImpl employeeService;

//   @Test
// public void createEmployeeTest() {
//     // Create a mock of the EmployeeRepository
//     EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
    
//     // Create an instance of the EmployeeService and pass the mocked repository
//     EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);
    
//     // Create a sample Employee object
//     Employee newEmployee = new Employee(1, "ray", "goh", "rkapril@me.com", "93838360", LocalDate.of(2015, 3, 18));

//     // Mock the save method of the employeeRepository to return the newEmployee object
//     when(employeeRepository.save(any(Employee.class))).thenReturn(newEmployee);

//     // Call the createEmployee method
//     Employee savedEmployee = employeeService.createEmployee(newEmployee);

//     // Verify that the save method of the employeeRepository was called with the newEmployee object
//     verify(employeeRepository, times(1)).save(newEmployee);

//     // Assert the expected results
//     assertEquals(newEmployee, savedEmployee);
// }


  @Test
  public void getAllEmployeesTest() {
    when(employeeRepository.findAll()).thenReturn(Arrays.asList(new Employee(1, "ray", "goh", "rkapril@me.com", "93838360", LocalDate.of(2015, 3, 18)), new Employee(2, "Peter", "Tan", "pt@me.com", "93838360", LocalDate.of(2015, 3, 18))));

    // Act
    List<Employee> allEmployees = employeeService.getAllEmployees();

    // Assert
    assertEquals(2, allEmployees.size());
  }

  @Test
  public void getEmployeeTest() {
    Employee newEmployee = new Employee(1, "ray", "goh", "rkapril@me.com", "93838360", LocalDate.of(2015, 3, 18));

    when(employeeRepository.findById(1)).thenReturn(Optional.of(newEmployee));

    // Act
    Employee foundEmployee = employeeService.getEmployee(1);

    // Assert
    assertEquals(newEmployee, foundEmployee);

  }

  @Test
  public void updateEmployeeTest() {

    // Create the updated employee
    Employee updatedEmployee = new Employee(1, "ray", "goh", "rkapril@me.com", "93838360", LocalDate.of(2015, 3, 18));

    // Mock the behavior of employeeRepository.findById
    when(employeeRepository.findById(1)).thenReturn(
        Optional.of(new Employee(1, "ray", "goh", "rkapril@me.com", "93838360", LocalDate.of(2015, 3, 18))));

    // Mock the behavior of employeeRepository.save
    when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

    // Update the employee
    Employee savedEmployee = employeeService.updateEmployee(1, updatedEmployee);

    // Assert
    assertEquals(updatedEmployee, savedEmployee);
  }

  @Test
  public void deleteEmployeeTest() {
    // Create the employee ID to delete
    int employeeId = 1;

    // Mock the behavior of employeeRepository.findById
    when(employeeRepository.findById(1)).thenReturn(
        Optional.of(new Employee(employeeId, "ray", "goh", "rkapril@me.com", "93838360", LocalDate.of(2015, 3, 18))));

    // Call the deleteEmployee method
    employeeService.deleteEmployee(employeeId);

    // Verify the behavior
    verify(employeeRepository, times(1)).deleteById(employeeId);
  }
}
