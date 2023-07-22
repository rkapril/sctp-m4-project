package com.example.hrmanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.hr_management.HrManagementApplication;
import com.example.hr_management.entity.Department;
import com.example.hr_management.repository.DepartmentRepository;
import com.example.hr_management.service.serviceImpl.DepartmentServiceImpl;

@SpringBootTest(classes = HrManagementApplication.class)
public class DepartmentServiceImplTest {

  // Create a mock of the DepartmentRepository
  @Mock
  private DepartmentRepository departmentRepository;

  // Inject the mocks as dependencies
  @InjectMocks
  DepartmentServiceImpl departmentService;

  @Test
  public void saveDepartmentTest() {
    // Mock the data
    Department newDepartment = new Department(1, "IT");

    // Mock the repo method
    when(departmentRepository.save(newDepartment)).thenReturn(newDepartment);

    // Act
    Department savedDepartment = departmentService.saveDepartment(newDepartment);

    // Assert
    verify(departmentRepository, times(1)).save(newDepartment);
    assertEquals(newDepartment, savedDepartment);
  }

  @Test
  public void getAllDepartmentsTest() {
    when(departmentRepository.findAll()).thenReturn(Arrays.asList(new Department(1, "IT"), new Department(2, "HR")));

    // Act
    List<Department> allDepartments = departmentService.getAllDepartments();

    // Assert
    assertEquals(2, allDepartments.size());
  }

  @Test
  public void getDepartmentTest() {
    Department newdepartment = new Department(1, "IT");

    when(departmentRepository.findById(1)).thenReturn(Optional.of(newdepartment));

    // Act
    Department foundDepartment = departmentService.getDepartment(1);

    // Assert
    assertEquals(newdepartment, foundDepartment);

  }

  @Test
  public void updateDepartmentTest() {
    // Create the updated department
    Department updatedDepartment = new Department(1, "HR");

    // Mock the behavior of departmentRepository.findById
    when(departmentRepository.findById(1)).thenReturn(Optional.of(new Department(1, "IT")));

    // Mock the behavior of departmentRepository.save
    when(departmentRepository.save(any(Department.class))).thenReturn(updatedDepartment);

    // Update the department
    Department savedDepartment = departmentService.updateDepartment(1, updatedDepartment);

    // Assert
    assertEquals(updatedDepartment, savedDepartment);
  }

  @Test
  public void deleteDepartmentTest() {
    // Create the department ID to delete
    int departmentId = 1;

    // Mock the behavior of departmentRepository.findById
    when(departmentRepository.findById(1)).thenReturn(Optional.of(new Department(departmentId, "IT")));

    // Call the deleteDepartment method
    departmentService.deleteDepartment(departmentId);

    // Verify the behavior
    verify(departmentRepository, times(1)).deleteById(departmentId);
  }

}
