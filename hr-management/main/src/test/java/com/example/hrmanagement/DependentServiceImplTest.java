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
import com.example.hr_management.entity.Dependent;
import com.example.hr_management.repository.DependentRepository;
import com.example.hr_management.service.serviceImpl.DependentServiceImpl;

@SpringBootTest(classes = HrManagementApplication.class)
public class DependentServiceImplTest {

  // Create a mock of the DependentRepository
  @Mock
  private DependentRepository dependentRepository;

  // Inject the mocks as dependencies
  @InjectMocks
  DependentServiceImpl dependentService;

  @Test
  public void createDependent() {
    // Mock the data
    Dependent newDependent = new Dependent(1, "Peter", "Tan", "Son");

    // Mock the repo method
    when(dependentRepository.save(newDependent)).thenReturn(newDependent);

    // Act
    Dependent saveDependent = dependentService.createDependent(newDependent);

    // Asert
    verify(dependentRepository, times(1)).save(newDependent);
    assertEquals(newDependent, saveDependent);
  }

  @Test
  public void getAllDependentsTest() {
    when(dependentRepository.findAll()).thenReturn(Arrays.asList(new Dependent(1, "Peter", "Tan", "Son"), new Dependent(2, "Mark", "Tan", "Son")));

    // Act
    List<Dependent> allDependents = dependentService.getAllDependents();

    // Assert
    assertEquals(2, allDependents.size());
  }

  @Test
  public void getDependentTest() {
    Dependent newdependent = new Dependent(1, "Peter", "Tan", "Son");

    when(dependentRepository.findById(1)).thenReturn(Optional.of(newdependent));

    // Act
    Dependent foundDependent = dependentService.getDependent(1);

    // Assert
    assertEquals(newdependent, foundDependent);
  }

  @Test
  public void updateDependentTest() {
    // Create the updated dependent
    Dependent updatedDependent = new Dependent(1, "Peter", "Tan", "Son");

    // Mock the behavior of dependentRepository.findById
    when(dependentRepository.findById(1)).thenReturn(Optional.of(new Dependent(1, "Jack", "Tan", "Son")));

    // Mock the behavior of departmentRepository.save
    when(dependentRepository.save(any(Dependent.class))).thenReturn(updatedDependent);

    // Updated the dependent
    Dependent savedDependent = dependentService.updateDependent(1, updatedDependent);

    // Assert
    assertEquals(updatedDependent, savedDependent);
  }

  @Test
  public void deleteDependentTest() {
    // Create the dependent ID to delete
    int dependentId = 1;

    // Mock the behavior of the dependentRepository.findById
    when(dependentRepository.findById(1)).thenReturn(Optional.of(new Dependent(dependentId, "Peter", "Tan", "Son")));

    // Call the deleteDependent method
    dependentService.deleteDependent(dependentId);

    // Verify the behavior
    verify(dependentRepository, times(1)).deleteById(dependentId);
  }

}
