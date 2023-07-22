package com.example.hr_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hr_management.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
