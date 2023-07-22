package com.example.hr_management.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "employee")
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private int id;

  @Column(name = "first_name", nullable = false)
  @NotBlank(message = "First name is mandatory.")
  @Size(min = 2, max = 15, message = "First name must be between 2 and 15 characters.")
  private String firstName;

  @Column(name = "last_name", nullable = false)
  @NotBlank(message = "Last name is mandatory.")
  @Size(min = 2, max = 15, message = "Last name must be between 2 and 15 characters.")
  private String lastName;

  @Column(name = "email", nullable = false)
  @Email(message = "Email must be valid.")
  private String email;

  @Column(name = "phone_number", nullable = false)
  @NotBlank(message = "Phone number is mandatory.")
  @Size(min = 8, max = 8, message = "Phone number must be valid.")
  private String phoneNumber;

  @Column(name = "hire_date", nullable = false)
  @NotNull(message = "Hire date is mandatory. Please provide the date in the format yyyy-MM-dd.")
  @PastOrPresent(message = "Hire date must be in the past or present.")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate hireDate;

  @ManyToOne
  @JoinColumn(name = "job_id")
  private Job job;

  public Job getJob() {
    return this.job;
  }

  public void setJob(Job job) {
    this.job = job;
  }

  @ManyToOne
  @JoinColumn(name = "department_id")
  private Department department;

  public Employee(int id, String firstName, String lastName, String email, String phoneNumber, LocalDate hireDate,
      Job job, Department department) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.hireDate = hireDate;
    this.job = job;
    this.department = department;
  }

  public Department getDepartment() {
    return this.department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public Employee() {
  }

  public Employee(int id, String firstName, String lastName, String email, String phoneNumber, LocalDate hireDate) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.hireDate = hireDate;
  }

  public Employee(int id, String firstName, String lastName, String email, String phoneNumber, LocalDate hireDate,
      Department department) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.hireDate = hireDate;
    this.department = department;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public LocalDate getHireDate() {
    return this.hireDate;
  }

  public void setHireDate(LocalDate hireDate) {
    this.hireDate = hireDate;
  }

}
