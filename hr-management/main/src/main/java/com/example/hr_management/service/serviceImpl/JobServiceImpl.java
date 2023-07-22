package com.example.hr_management.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.hr_management.entity.Job;
import com.example.hr_management.exception.JobNotFoundException;
import com.example.hr_management.repository.JobRepository;
import com.example.hr_management.service.JobService;

@Service
public class JobServiceImpl implements JobService {

  private JobRepository jobRepository;

  public JobServiceImpl(JobRepository jobRepository) {
    this.jobRepository = jobRepository;
  }

  @Override
  public Job saveJob(Job job) {
    Job newJob = jobRepository.save(job);
    return newJob;
  }

  @Override
  public Job getJob(int id) {
    Optional<Job> wrappedFoundJob = jobRepository.findById(id);

    if (!wrappedFoundJob.isPresent()) {
      throw new JobNotFoundException(id);
    }

    Job foundJob = wrappedFoundJob.get();

    return foundJob;
  }

  @Override
  public List<Job> getAllJobs() {
    List<Job> allJobs = jobRepository.findAll();
    return allJobs;
  }

  @Override
  public Job updateJob(int id, Job job) {
    Optional<Job> wrappedJobToUpdate = jobRepository.findById(id);

    if (!wrappedJobToUpdate.isPresent()) {
      throw new JobNotFoundException(id);
    }

    Job jobToUpdate = wrappedJobToUpdate.get();
    jobToUpdate.setJobTitle(job.getJobTitle());
    jobToUpdate.setSalary(job.getSalary());
    return jobRepository.save(jobToUpdate);
  }

  @Override
  public void deleteJob(int id) {
    jobRepository.deleteById(id);
  }

}
