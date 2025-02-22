package com.demo.service;

import com.demo.entites.Activity;
import com.demo.entites.Education;
import com.demo.entites.Experience;
import com.demo.entites.Jobseeker;
import com.demo.entites.User;
import com.demo.exception.UserNotFoundException;
import com.demo.repository.JobseekerRepository;
import com.demo.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class JobseekerServiceImplTest {

	@Mock
	private JobseekerRepository jobseekerRepository;

	@Mock
	private ActivityService activityService;

	@Mock
	private EntityManager entityManager;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private JobseekerServiceImpl jobseekerServiceImpl;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllJobseekers() {
		Jobseeker jobseeker = new Jobseeker();
		when(jobseekerRepository.findAll()).thenReturn(Arrays.asList(jobseeker));

		List<Jobseeker> result = jobseekerServiceImpl.getAllJobseekers();

		assertEquals(1, result.size());
		assertEquals(jobseeker, result.get(0));
	}

	@Test
	public void testGetJobseekerById_Success() {
		Jobseeker jobseeker = new Jobseeker();
		when(jobseekerRepository.findById(anyLong())).thenReturn(Optional.of(jobseeker));

		ResponseEntity<Jobseeker> response = jobseekerServiceImpl.getJobseekerById(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(jobseeker, response.getBody());
	}

	@Test
	public void testGetJobseekerById_NotFound() {
		when(jobseekerRepository.findById(anyLong())).thenReturn(Optional.empty());

		ResponseEntity<Jobseeker> response = jobseekerServiceImpl.getJobseekerById(1L);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	public void testAddJobseeker_Success() {
		Jobseeker jobseeker = new Jobseeker();
		User user = new User();
		user.setUserId(1L);
		jobseeker.setUser(user);
		Education education = new Education();
		Experience experience = new Experience();
		jobseeker.setEducation(Arrays.asList(education));
		jobseeker.setExperience(Arrays.asList(experience));

		when(userRepository.existsById(anyLong())).thenReturn(true);
		when(entityManager.merge(any(Education.class))).thenReturn(education);
		when(entityManager.merge(any(Experience.class))).thenReturn(experience);
		when(entityManager.merge(any(User.class))).thenReturn(user);
		when(entityManager.merge(any(Jobseeker.class))).thenReturn(jobseeker);
		when(jobseekerRepository.save(any(Jobseeker.class))).thenReturn(jobseeker);

		Jobseeker result = jobseekerServiceImpl.addJobseeker(jobseeker);

		assertEquals(jobseeker, result);
		verify(activityService, times(1)).addActivity(any(Activity.class));
	}

	@Test
	public void testAddJobseeker_UserNotFound() {
		Jobseeker jobseeker = new Jobseeker();
		User user = new User();
		user.setUserId(1L);
		jobseeker.setUser(null);
		Education education = new Education();
		Experience experience = new Experience();
		jobseeker.setEducation(Arrays.asList(education));
		jobseeker.setExperience(Arrays.asList(experience));

		when(userRepository.existsById(anyLong())).thenReturn(false);

		assertThrows(UserNotFoundException.class, () -> jobseekerServiceImpl.addJobseeker(jobseeker));
	}

	@Test
	public void testDeleteJobseeker_Success() {
		Jobseeker jobseeker = new Jobseeker();
		when(jobseekerRepository.findById(anyLong())).thenReturn(Optional.of(jobseeker));

		ResponseEntity<String> response = jobseekerServiceImpl.deleteJobseeker(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Jobseeker deleted successfully", response.getBody());
		verify(activityService, times(1)).addActivity(any(Activity.class));
		verify(jobseekerRepository, times(1)).deleteById(1L);
	}

	@Test
	public void testDeleteJobseeker_NotFound() {
		when(jobseekerRepository.findById(anyLong())).thenReturn(Optional.empty());

		ResponseEntity<String> response = jobseekerServiceImpl.deleteJobseeker(1L);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Jobseeker not found with given id", response.getBody());
	}

	@Test
	public void testUpdateJobseeker() {
		Jobseeker jobseeker = new Jobseeker();
		when(jobseekerRepository.save(any(Jobseeker.class))).thenReturn(jobseeker);

		Jobseeker result = jobseekerServiceImpl.updateJobseeker(jobseeker);

		assertEquals(jobseeker, result);
		verify(activityService, times(1)).addActivity(any(Activity.class));
	}

	@Test
	public void testGetJobseekersByQualification() {
		Jobseeker jobseeker = new Jobseeker();
		when(jobseekerRepository.getJobseekersByQualification(anyString())).thenReturn(Arrays.asList(jobseeker));

		List<Jobseeker> result = jobseekerServiceImpl.getJobseekersByQualification("degree");

		assertEquals(1, result.size());
		assertEquals(jobseeker, result.get(0));
	}

	@Test
	public void testGetJobseekerByExperience() {
		Jobseeker jobseeker = new Jobseeker();
		when(jobseekerRepository.getJobseekerByExperience(anyInt())).thenReturn(Arrays.asList(jobseeker));

		List<Jobseeker> result = jobseekerServiceImpl.getJobseekerByExperience(5);

		assertEquals(1, result.size());
		assertEquals(jobseeker, result.get(0));
	}
}