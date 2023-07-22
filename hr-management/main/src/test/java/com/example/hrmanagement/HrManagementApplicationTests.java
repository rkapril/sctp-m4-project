package com.example.hrmanagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.hr_management.HrManagementApplication;
import com.example.hr_management.entity.Department;
import com.example.hr_management.entity.Dependent;
import com.example.hr_management.entity.Employee;
import com.example.hr_management.entity.Job;
import com.example.hr_management.repository.DepartmentRepository;
import com.example.hr_management.repository.JobRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

@SpringBootTest(classes = HrManagementApplication.class)
@AutoConfigureMockMvc
class HrManagementApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private ObjectMapper objectmapper;

	@BeforeEach
	void setup() {
		departmentRepository.save(new Department(1, "admin"));
		departmentRepository.save(new Department(2, "Sales"));
		jobRepository.save(new Job(1, "Admin", 5000));
		jobRepository.save(new Job(2, "Manager", 8000));
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void getDepartmentByIdTest() throws Exception {
		// Step 1: Build a GET request to /departments/1
		RequestBuilder request = MockMvcRequestBuilders.get("/departments/1");

		// Step 2: Perform the request and get the result
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1));

	}

	@Test
	public void getJobByIdTest() throws Exception {
		// Step 1: Build a GET request to /jobs/1
		RequestBuilder request = MockMvcRequestBuilders.get("/jobs/1");

		// Step 2: Perform ther request and get the result
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1));

	}

	@Test
	public void getEmployeeByIdTest() throws Exception {
		// Step 1: Build a GET request to /employees/1
		RequestBuilder request = MockMvcRequestBuilders.get("/employees/1");

		// Step 2: Perform the request and get the result
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	public void getDependentByIdTest() throws Exception {
		// Step 1: Build a GET request to /dependents/1
		RequestBuilder request = MockMvcRequestBuilders.get("/dependents/1");

		// Step 2: Perform the request and get the result
		mockMvc.perform(request).andExpect(status().isNotFound())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void getAllDepartmentsTest() throws Exception {
		// Step 1: Build the request to get all departments
		RequestBuilder request = MockMvcRequestBuilders.get("/departments");

		// Step 2: Perform the request and get the result
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.size()").value(2));
	}

	@Test
	public void getAllJobsTest() throws Exception {
		// Step 1: Build the request to get all jobs
		RequestBuilder request = MockMvcRequestBuilders.get("/jobs");

		// Step 2: Perform the request and get the result
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.size()").value(2));

	}

	@Test
	public void getAllEmployeesTest() throws Exception {
		// Step 1: Build the request to get all employees
		RequestBuilder request = MockMvcRequestBuilders.get("/employees");

		// Step 2: Perform the request and get the result
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.size()").value(1));

	}

	@Test
	public void getAllDependentsTest() throws Exception {
		// Step 1: Build the request to get all employees
		RequestBuilder request = MockMvcRequestBuilders.get("/dependents");

		// Step 2: Perform the request and get the result
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.size()").value(0));

	}

	@Test
	public void validDepartmentCreationtest() throws Exception {
		// Step 1: Build the request to create a department
		Department newDepartment = new Department(3, "IT");

		// Step 2: Convert the Java object to JSON
		String newDepartmentAsJson = objectmapper.writeValueAsString(newDepartment);

		// Step 3: Build the request to create a department
		RequestBuilder request = MockMvcRequestBuilders.post("/departments").contentType(MediaType.APPLICATION_JSON)
				.content(newDepartmentAsJson);

		// Step 4: Perform the request and get the result
		mockMvc.perform(request).andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id").value(3))
				.andExpect(jsonPath("$.departmentName").value("IT"));
	}

	@Test
	public void validJobCreationTest() throws Exception {
		// Step 1: Build the request to create a department
		Job newJob = new Job(3, "Engineer", 5000);

		// Step 2: Convert the Java object to JSON
		String newJobAsJson = objectmapper.writeValueAsString(newJob);

		// Step 3: Build the request to create a job
		RequestBuilder request = MockMvcRequestBuilders.post("/jobs").contentType(MediaType.APPLICATION_JSON)
				.content(newJobAsJson);

		// Step 4: Perform the request and get the result
		mockMvc.perform(request).andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id").value(3))
				.andExpect(jsonPath("$.jobTitle").value("Engineer")).andExpect(jsonPath("$.salary").value(5000));

	}

	@Test
	public void validEmployeeCreationTest() throws Exception {
		// Step 1: Build the request to create a employee
		Employee newEmployee = new Employee(1, "Peter", "Tan", "pt@a.com", "12345678", LocalDate.of(2015, 3, 18));

		// Step 2: Convert the Java object to JSON
		String newEmployeeAsJson = objectmapper.writeValueAsString(newEmployee);

		// Step 3: Build the request to create an employee
		RequestBuilder request = MockMvcRequestBuilders.post("/employees").contentType(MediaType.APPLICATION_JSON)
				.content(newEmployeeAsJson).param("departmentId", "1").param("jobId", "1");

		// Step 4: Perform the request and get the result
		mockMvc.perform(request).andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.firstName").value("Peter")).andExpect(jsonPath("$.lastName").value("Tan"))
				.andExpect(jsonPath("$.email").value("pt@a.com"))
				.andExpect(jsonPath("$.hireDate").value(LocalDate.of(2015, 3, 18).toString()));
	}

	@Test
	public void validDependentCreationTest() throws Exception {
		// Step 1: Create a dependent object
		Dependent newdependent = new Dependent(1, "Mark", "Tan", "Son");

		// Step 2: Convert the dependent object to JSON
		String depedentAsJson = objectmapper.writeValueAsString(newdependent);

		// Step 3: Build the request to add the dependent to an employee
		RequestBuilder request = MockMvcRequestBuilders.post("/employees/1/dependents")
				.contentType(MediaType.APPLICATION_JSON).content(depedentAsJson);

		// Step 4: Perform the request and get the result
		mockMvc.perform(request).andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.firstName").value("Mark")).andExpect(jsonPath("$.lastName").value("Tan"))
				.andExpect(jsonPath("$.relationship").value("Son"));
	}

	@Test
	public void invalidDepartmentCreationTest() throws Exception {
		// Step 1: Create an invalid Department
		Department invalidDepartment = new Department(4, " ");

		// Step 2: Convert the Java object to JSON
		String invalidDepartmentAsJson = objectmapper.writeValueAsString(invalidDepartment);

		// Step 3: Build the request
		RequestBuilder request = MockMvcRequestBuilders.post("/departments").contentType(MediaType.APPLICATION_JSON)
				.content(invalidDepartmentAsJson);

		// Step 4: Perform the request and get the result
		mockMvc.perform(request).andExpect(status().isBadRequest())
				.andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"));

	}

	@Test
	public void invalidJobCreationTest() throws Exception {
		// Step 1: Create an invalid Job
		Job invalidJob = new Job(4, " ", 5000);

		// Step 2: Convert the Java object to JSON
		String invalidJobAsJson = objectmapper.writeValueAsString(invalidJob);

		// Step 3: Build the request
		RequestBuilder request = MockMvcRequestBuilders.post("/jobs").contentType(MediaType.APPLICATION_JSON)
				.content(invalidJobAsJson);

		// Step 4: Perform the request and get the result
		mockMvc.perform(request).andExpect(status().isBadRequest())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void invalidEmployeeCreationTest() throws Exception {
		// Step 1: Create an invalid Employee
		Employee invalidEmployee = new Employee(2, "Jack", " ", "jt@a.com", "12345678", LocalDate.of(2015, 3, 18));

		// Step 2: Convert the Java object to JSON
		String invalidEmployeeAsJson = objectmapper.writeValueAsString(invalidEmployee);

		// Step 3: Build the request
		RequestBuilder request = MockMvcRequestBuilders.post("/employees").contentType(MediaType.APPLICATION_JSON)
				.content(invalidEmployeeAsJson).param("departmentId", "1").param("jobId", "1");

		// Step 4: Perform the request and get the result
		mockMvc.perform(request).andExpect(status().isBadRequest())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void updateDepartmentTest() throws Exception {
		// Step 1: Create a Department object with updated values
		Department updatedDepartment = new Department(1, "HR");

		// Step 2: Convert the Department object to JSON
		String updatedDepartmentAsJson = objectmapper.writeValueAsString(updatedDepartment);

		// Step 3: Build the request to update the department with ID 1
		RequestBuilder request = MockMvcRequestBuilders.put("/departments/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(updatedDepartmentAsJson);

		// Step 4: Perform the request and get the result
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.departmentName").value("HR"));
	}

	@Test
	public void updateJobTest() throws Exception {
		// Step 1: Create a Department object with updated values
		Job updatedJob = new Job(1, "Sales", 6000);

		// Step 2: Convert the Department object to JSON
		String updatedJobAsJson = objectmapper.writeValueAsString(updatedJob);

		// Step 3: Build the request to update the department with ID 1
		RequestBuilder request = MockMvcRequestBuilders.put("/jobs/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(updatedJobAsJson);

		// Step 4: Perform the request and get the result
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.jobTitle").value("Sales")).andExpect(jsonPath("$.salary").value("6000"));
	}

}
