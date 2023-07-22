package com.example.hrmanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.hr_management.HrManagementApplication;
import com.example.hr_management.entity.Job;
import com.example.hr_management.repository.JobRepository;
import com.example.hr_management.service.serviceImpl.JobServiceImpl;

@SpringBootTest(classes = HrManagementApplication.class)
public class JobServiceImplTest {

  // Create a mock of the JobRepository
  @Mock
  private JobRepository jobRepository;

  // Inject the mocks as dependencies
  @InjectMocks
  JobServiceImpl jobService;

  @Test
  public void saveJobTest() {
    // Mock the data
    Job newJob = new Job(1, "Admin", 5000);

    // Mock the repo method
    when(jobRepository.save(newJob)).thenReturn(newJob);

    // Act
    Job savedJob = jobService.saveJob(newJob);

    // Assert
    verify(jobRepository, times(1)).save(newJob);
    assertEquals(newJob, savedJob);
  }

  @Test
  public void getAllJobsTest() {
    when(jobRepository.findAll()).thenReturn(Arrays.asList(new Job(1, "admin", 5000), new Job(2, "IT", 6000)));

    // Act
    List<Job> allJobs = jobService.getAllJobs();

    // Assert
    assertEquals(2, allJobs.size());
  }

  @Test
  public void getJobTest() {
    Job newjob = new Job(1, "admin", 5000);

    when(jobRepository.findById(1)).thenReturn(Optional.of(newjob));

    // Act
    Job foundJob = jobService.getJob(1);

    // Assert
    assertEquals(newjob, foundJob);
  }

  @Test
  public void updateJobTest() {
    // Create the updated job
    Job updatedJob = new Job(1, "admin", 5000);

    // Mock the behavior of JobRepository.findById
    when(jobRepository.findById(1)).thenReturn(Optional.of(new Job(1, "IT", 8000)));

    // Mock the behavior of jobRepository.save
    when(jobRepository.save(any(Job.class))).thenReturn(updatedJob);

    // Update the job
    Job savedJob = jobService.updateJob(1, updatedJob);

    // Assert
    assertEquals(updatedJob, savedJob);
  }

  @Test
  public void deleteJobTest() {
    // Create the job ID to delete
    int jobId = 1;

    // Mock the behavior of jobRepository.findById
    when(jobRepository.findById(1)).thenReturn(Optional.of(new Job(jobId, "admin", 5000)));

    // Call the deleteJob method
    jobService.deleteJob(jobId);

    // Verify the behavior
    verify(jobRepository, times(1)).deleteById(jobId);
  }

}
