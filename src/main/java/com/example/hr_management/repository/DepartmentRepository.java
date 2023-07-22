package com.example.hr_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hr_management.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
