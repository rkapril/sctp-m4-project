package com.example.hr_management.service;

import java.util.List;

import com.example.hr_management.entity.Department;

public interface DepartmentService {
  Department saveDepartment(Department department);

  Department getDepartment(int id);

  List<Department> getAllDepartments();

  Department updateDepartment(int id, Department department);

  void deleteDepartment(int id);

}
