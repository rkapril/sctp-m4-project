package com.example.hr_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hr_management.entity.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {

}
