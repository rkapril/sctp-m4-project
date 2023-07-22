package com.example.hr_management.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hr_management.entity.Department;
import com.example.hr_management.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

  private DepartmentService departmentService;

  public DepartmentController(DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

  // 1. Create
  @PostMapping("")
  public ResponseEntity<?> saveDepartment(@Valid @RequestBody Department department,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      String errorMessage = bindingResult.getFieldErrors()
          .stream()
          .map(fieldError -> fieldError.getDefaultMessage())
          .findFirst()
          .orElse("Validation failed.");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
    Department newDepartment = departmentService.saveDepartment(department);
    return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
  }

  // Read
  @GetMapping("/{id}")
  public ResponseEntity<Department> getDepartment(@PathVariable int id) {
    Department foundDepartment = departmentService.getDepartment(id);
    return new ResponseEntity<>(foundDepartment, HttpStatus.OK);
  }

  // Read All
  @GetMapping("")
  public ResponseEntity<List<Department>> getAllDepartments() {
    List<Department> allDepartments = departmentService.getAllDepartments();
    return new ResponseEntity<>(allDepartments, HttpStatus.OK);
  }

  // Update
  @PutMapping("/{id}")
  public ResponseEntity<?> updateDepartment(@PathVariable int id, @Valid @RequestBody Department department,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      String errorMessage = bindingResult.getFieldErrors()
          .stream()
          .map(fieldError -> fieldError.getDefaultMessage())
          .findFirst()
          .orElse("Validation failed.");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
    Department updatedDepartment = departmentService.updateDepartment(id, department);
    return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
  }

  // Delete
  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable int id) {
    departmentService.deleteDepartment(id);
    return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
  }
}
