package com.example.hr_management.service;

import java.util.List;

import com.example.hr_management.entity.Job;

public interface JobService {
  Job saveJob(Job job);

  Job getJob(int id);

  List<Job> getAllJobs();

  Job updateJob(int id, Job job);

  void deleteJob(int id);

}
