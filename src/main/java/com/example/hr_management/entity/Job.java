package com.example.hr_management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "job")
public class Job {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private int id;

  @Column(name = "job_title", nullable = false)
  @NotBlank(message = "Job title name is mandatory.")
  @Size(min = 2, max = 15, message = "Job title must be between 2 and 15 characters.")
  private String jobTitle;

  @Column(name = "salary", nullable = false)
  @Min(value = 0, message = "Salary must be a positive number.")
  private int salary;

  public Job() {
  }

  public Job(int id, String jobTitle, int salary) {
    this.id = id;
    this.jobTitle = jobTitle;
    this.salary = salary;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getJobTitle() {
    return this.jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public int getSalary() {
    return this.salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

}
