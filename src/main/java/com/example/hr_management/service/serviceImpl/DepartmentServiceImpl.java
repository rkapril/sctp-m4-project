package com.example.hr_management.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.hr_management.exception.DepartmentNotFoundException;
import com.example.hr_management.entity.Department;
import com.example.hr_management.repository.DepartmentRepository;
import com.example.hr_management.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
  private DepartmentRepository departmentRepository;

  public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  public Department saveDepartment(Department department) {
    Department newDepartment = departmentRepository.save(department);
    return newDepartment;
  }

  @Override
  public Department getDepartment(int id) {
    Optional<Department> wrappedFoundDepartment = departmentRepository.findById(id);

    if (!wrappedFoundDepartment.isPresent()) {
      throw new DepartmentNotFoundException(id);
    }

    Department foundDepartment = wrappedFoundDepartment.get();

    return foundDepartment;
  }

  @Override
  public List<Department> getAllDepartments() {
    List<Department> allDepartments = departmentRepository.findAll();
    return allDepartments;
  }

  @Override
  public Department updateDepartment(int id, Department department) {
    Optional<Department> wrappedDepartmentToUpdate = departmentRepository.findById(id);

    if (!wrappedDepartmentToUpdate.isPresent()) {
      throw new DepartmentNotFoundException(id);
    }

    Department departmentToUpdate = wrappedDepartmentToUpdate.get();

    departmentToUpdate.setDepartmentName(department.getDepartmentName());
    return departmentRepository.save(departmentToUpdate);
  }

  @Override
  public void deleteDepartment(int id) {
    departmentRepository.deleteById(id);
  }

}
