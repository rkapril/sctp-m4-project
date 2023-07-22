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
import org.springframework.web.bind.annotation.RestController;

import com.example.hr_management.entity.Job;
import com.example.hr_management.service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {

  private JobService jobService;

  public JobController(JobService jobService) {
    this.jobService = jobService;
  }

  // 1. Create
  @PostMapping("")
  public ResponseEntity<?> saveJob(@Valid @RequestBody Job job) {
    Job newJob = jobService.saveJob(job);
    return new ResponseEntity<>(newJob, HttpStatus.CREATED);
  }

  // Read
  @GetMapping("/{id}")
  public ResponseEntity<Job> getJob(@PathVariable int id) {
    Job foundJob = jobService.getJob(id);
    return new ResponseEntity<>(foundJob, HttpStatus.OK);
  }

  // Read All
  @GetMapping("")
  public ResponseEntity<List<Job>> getAllJobs() {
    List<Job> allJobs = jobService.getAllJobs();
    return new ResponseEntity<>(allJobs, HttpStatus.OK);
  }

  // Update
  @PutMapping("/{id}")
  public ResponseEntity<?> updateJob(@PathVariable int id, @Valid @RequestBody Job job) {
    Job updatedJob = jobService.updateJob(id, job);
    return new ResponseEntity<>(updatedJob, HttpStatus.OK);
  }

  // Delete
  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteJob(@PathVariable int id) {
    jobService.deleteJob(id);
    return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
  }

}
