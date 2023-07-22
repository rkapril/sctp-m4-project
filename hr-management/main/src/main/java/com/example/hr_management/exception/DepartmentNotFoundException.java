package com.example.hr_management.exception;

public class DepartmentNotFoundException extends RuntimeException {
  public DepartmentNotFoundException(int id) {
    super("Department with id " + id + " not found.");
  }
}
