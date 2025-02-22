package com.demo.service;

import com.demo.entites.Activity;
import com.demo.entites.Employer;
import com.demo.entites.Jobpost;
import com.demo.entites.User;
import com.demo.repository.EmployerRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class EmployerServiceImplTest {

	@Mock
	private EmployerRepository employerRepository;

	@Mock
	private ActivityService activityService;

	@Mock
	private EntityManager entityManager;

	@InjectMocks
	private EmployerServiceImpl employerServiceImpl;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAddEmployer() {
		Employer employer = new Employer();
		User user = new User();
		employer.setUser(user);
		Jobpost jobpost = new Jobpost();
		employer.setJobPosts(Arrays.asList(jobpost));

		when(entityManager.merge(any(Jobpost.class))).thenReturn(jobpost);
		when(entityManager.merge(any(User.class))).thenReturn(user);
		when(entityManager.merge(any(Employer.class))).thenReturn(employer);
		when(employerRepository.save(any(Employer.class))).thenReturn(employer);

		Employer result = employerServiceImpl.addEmployer(employer);

		assertEquals(employer, result);
		verify(activityService, times(1)).addActivity(any(Activity.class));
	}

	@Test
	public void testAddEmployer_NoJobPosts() {
		Employer employer = new Employer();
		User user = new User();
		employer.setUser(user);
		employer.setJobPosts(null);

		when(entityManager.merge(any(User.class))).thenReturn(user);
		when(entityManager.merge(any(Employer.class))).thenReturn(employer);
		when(employerRepository.save(any(Employer.class))).thenReturn(employer);

		Employer result = employerServiceImpl.addEmployer(employer);

		assertEquals(employer, result);
		verify(activityService, times(1)).addActivity(any(Activity.class));
	}

	@Test
	public void testGetAllEmployers() {
		Employer employer = new Employer();
		when(employerRepository.findAll()).thenReturn(Arrays.asList(employer));

		List<Employer> result = employerServiceImpl.getAllEmployers();

		assertEquals(1, result.size());
		assertEquals(employer, result.get(0));
	}

	@Test
	public void testGetEmployerById_Success() {
		Employer employer = new Employer();
		when(employerRepository.findById(anyLong())).thenReturn(Optional.of(employer));

		Optional<Employer> result = employerServiceImpl.getEmployerById(1L);

		assertEquals(employer, result.get());
	}

	@Test
	public void testGetEmployerById_NotFound() {
		when(employerRepository.findById(anyLong())).thenReturn(null);

		assertThrows(Error.class, () -> employerServiceImpl.getEmployerById(1L));
	}

	@Test
	public void testUpdateEmployer() {
		Employer employer = new Employer();
		User user = new User();
		employer.setUser(user);
		Jobpost jobpost = new Jobpost();
		employer.setJobPosts(Arrays.asList(jobpost));

		when(entityManager.merge(any(Jobpost.class))).thenReturn(jobpost);
		when(entityManager.merge(any(User.class))).thenReturn(user);
		when(entityManager.merge(any(Employer.class))).thenReturn(employer);
		when(employerRepository.save(any(Employer.class))).thenReturn(employer);

		Employer result = employerServiceImpl.updateEmployer(employer);

		assertEquals(employer, result);
		verify(activityService, times(1)).addActivity(any(Activity.class));
	}

	@Test
	public void testUpdateEmployer_NoJobPosts() {
		Employer employer = new Employer();
		User user = new User();
		employer.setUser(user);
		employer.setJobPosts(null);

		when(entityManager.merge(any(User.class))).thenReturn(user);
		when(entityManager.merge(any(Employer.class))).thenReturn(employer);
		when(employerRepository.save(any(Employer.class))).thenReturn(employer);

		Employer result = employerServiceImpl.updateEmployer(employer);

		assertEquals(employer, result);
		verify(activityService, times(1)).addActivity(any(Activity.class));
	}

	@Test
	public void testDeleteEmployer_Success() {
		Employer employer = new Employer();
		when(employerRepository.findById(anyLong())).thenReturn(Optional.of(employer));

		Optional<Employer> result = employerServiceImpl.deleteEmployer(1L);

		assertEquals(employer, result.get());
		verify(activityService, times(1)).addActivity(any(Activity.class));
		verify(employerRepository, times(1)).deleteById(1L);
	}

	@Test
	public void testDeleteEmployer_NotFound() {
		when(employerRepository.findById(anyLong())).thenReturn(null);

		assertThrows(Error.class, () -> employerServiceImpl.deleteEmployer(1L));
	}
}