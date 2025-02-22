package com.demo.controller;

import com.demo.entites.Jobseeker;
import com.demo.service.JobseekerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class JobseekerControllerTest {

	@Mock
	private JobseekerService jobseekerService;

	@InjectMocks
	private JobseekerController jobseekerController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllJobseekers() {
		Jobseeker jobseeker = new Jobseeker();
		when(jobseekerService.getAllJobseekers()).thenReturn(Arrays.asList(jobseeker));

		List<Jobseeker> response = jobseekerController.getAllJobseekers();

		assertEquals(1, response.size());
		assertEquals(jobseeker, response.get(0));
	}

	@Test
	public void testGetJobseekerById() {
		Jobseeker jobseeker = new Jobseeker();
		when(jobseekerService.getJobseekerById(1L)).thenReturn(new ResponseEntity<>(jobseeker, HttpStatus.OK));

		ResponseEntity<Jobseeker> response = jobseekerController.getJobseekerById(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(jobseeker, response.getBody());
	}

	@Test
	public void testGetJobseekerById_NotFound() {
		when(jobseekerService.getJobseekerById(1L)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));

		ResponseEntity<Jobseeker> response = jobseekerController.getJobseekerById(1L);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	public void testCreateJobseeker() {
		Jobseeker jobseeker = new Jobseeker();
		when(jobseekerService.addJobseeker(any(Jobseeker.class))).thenReturn(jobseeker);

		ResponseEntity<Jobseeker> response = jobseekerController.createJobseeker(jobseeker);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(jobseeker, response.getBody());
	}

	@Test
	public void testUpdateJobseeker() {
		Jobseeker jobseeker = new Jobseeker();
		when(jobseekerService.updateJobseeker(any(Jobseeker.class))).thenReturn(jobseeker);

		Jobseeker response = jobseekerController.updateJobseeker(jobseeker);

		assertEquals(jobseeker, response);
	}

	@Test
	public void testDeleteJobseeker() {
		when(jobseekerService.deleteJobseeker(1L)).thenReturn(new ResponseEntity<>("Jobseeker deleted successfully", HttpStatus.OK));

		ResponseEntity<String> response = jobseekerController.deleteJobseeker(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Jobseeker deleted successfully", response.getBody());
	}

	@Test
	public void testDeleteJobseeker_NotFound() {
		when(jobseekerService.deleteJobseeker(1L)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));

		ResponseEntity<String> response = jobseekerController.deleteJobseeker(1L);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	public void testGetJobseekerByQualification() {
		Jobseeker jobseeker = new Jobseeker();
		when(jobseekerService.getJobseekersByQualification("degree")).thenReturn(Arrays.asList(jobseeker));

		List<Jobseeker> response = jobseekerController.getJobseekerByQualification("degree");

		assertEquals(1, response.size());
		assertEquals(jobseeker, response.get(0));
	}

	@Test
	public void testGetJobseekerByExperience() {
		Jobseeker jobseeker = new Jobseeker();
		when(jobseekerService.getJobseekerByExperience(5)).thenReturn(Arrays.asList(jobseeker));

		List<Jobseeker> response = jobseekerController.getJobseekerByExperience(5);

		assertEquals(1, response.size());
		assertEquals(jobseeker, response.get(0));
	}
}