package com.example.hr_management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "department")
public class Department {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private int id;

  @Column(name = "department_name", nullable = false)
  @NotBlank(message = "Department name is mandatory.")
  @Size(min = 2, max = 15, message = "Department name must be between 2 and 15 characters.")
  private String departmentName;

  public Department() {
  }

  public Department(int id, String departmentName) {
    this.id = id;
    this.departmentName = departmentName;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDepartmentName() {
    return this.departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

}
