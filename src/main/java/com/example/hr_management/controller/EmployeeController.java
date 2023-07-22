package com.example.hr_management.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hr_management.entity.Department;
import com.example.hr_management.entity.Dependent;
import com.example.hr_management.entity.Employee;
import com.example.hr_management.entity.Job;
import com.example.hr_management.service.DepartmentService;
import com.example.hr_management.service.EmployeeService;
import com.example.hr_management.service.JobService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

  private EmployeeService employeeService;
  private DepartmentService departmentService;
  private JobService jobService;

  public EmployeeController(EmployeeService employeeService, DepartmentService departmentService,
      JobService jobService) {
    this.employeeService = employeeService;
    this.departmentService = departmentService;
    this.jobService = jobService;
  }

  // 1. Create
  @PostMapping("")
  public ResponseEntity<Employee> createEmployee(@RequestBody @Valid Employee employee,
      @RequestParam("departmentId") int departmentId, @RequestParam("jobId") int jobId) {

    Department department = departmentService.getDepartment(departmentId);
    employee.setDepartment(department);

    Job job = jobService.getJob(jobId);
    employee.setJob(job);

    Employee newEmployee = employeeService.createEmployee(employee);
    return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
  }

  // Read
  @GetMapping("/{id}")
  public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
    Employee foundEmployee = employeeService.getEmployee(id);
    return new ResponseEntity<>(foundEmployee, HttpStatus.OK);
  }

  // Read All
  @GetMapping("")
  public ResponseEntity<List<Employee>> getAllEmployees() {
    List<Employee> allEmployees = employeeService.getAllEmployees();
    return new ResponseEntity<>(allEmployees, HttpStatus.OK);
  }

  // Update
  @PutMapping("/{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {

    Employee updateEmployee = employeeService.updateEmployee(id, employee);
    return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
  }

  // Delete
  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable int id) {
    employeeService.deleteEmployee(id);
    return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
  }

  @PostMapping("/{id}/dependents")
  public ResponseEntity<Dependent> addDependentToEmployee(@PathVariable int id, @RequestBody Dependent dependent) {
    Dependent newDependent = employeeService.addDependentToEmployee(id, dependent);
    return new ResponseEntity<>(newDependent, HttpStatus.CREATED);
  }

}
