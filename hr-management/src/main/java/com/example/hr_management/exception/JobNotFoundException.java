package com.example.hr_management.exception;

public class JobNotFoundException extends RuntimeException {
  public JobNotFoundException(int id) {
    super("Job with id " + id + " not found.");
  }
}
