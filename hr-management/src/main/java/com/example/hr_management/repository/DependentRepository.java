package com.example.hr_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hr_management.entity.Dependent;

public interface DependentRepository extends JpaRepository<Dependent, Integer>{
    
}
