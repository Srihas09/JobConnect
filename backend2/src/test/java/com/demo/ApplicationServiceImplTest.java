package com.demo.service;

import com.demo.entites.Activity;
import com.demo.entites.Application;
import com.demo.entites.Employer;
import com.demo.entites.Jobpost;
import com.demo.entites.Jobseeker;
import com.demo.repository.ApplicationRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class ApplicationServiceImplTest {

	@Mock
	private ApplicationRepository applicationRepository;

	@Mock
	private ActivityService activityService;

	@Mock
	private EntityManager entityManager;

	@InjectMocks
	private ApplicationServiceImpl applicationServiceImpl;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAddApplication() {
		Application application = new Application();
		Jobseeker jobseeker = new Jobseeker();
		Jobpost jobpost = new Jobpost();
		Employer employer = new Employer();
		jobpost.setEmployer(employer);
		application.setJobSeeker(jobseeker);
		application.setJobPost(jobpost);

		when(entityManager.merge(any(Jobseeker.class))).thenReturn(jobseeker);
		when(entityManager.merge(any(Jobpost.class))).thenReturn(jobpost);
		when(applicationRepository.save(any(Application.class))).thenReturn(application);

		Application result = applicationServiceImpl.addApplication(application);

		assertEquals(application, result);
		verify(activityService, times(1)).addActivity(any(Activity.class));
	}

	@Test
	public void testGetAllApplications() {
		Application application = new Application();
		when(applicationRepository.findAll()).thenReturn(Arrays.asList(application));

		List<Application> result = applicationServiceImpl.getAllApplications();

		assertEquals(1, result.size());
		assertEquals(application, result.get(0));
	}

	@Test
	public void testGetApplicationsByJobseeker() {
		Application application = new Application();
		when(applicationRepository.getApplicationsByJobseeker(anyLong())).thenReturn(Arrays.asList(application));

		List<Application> result = applicationServiceImpl.getApplicationsByJobseeker(1L);

		assertEquals(1, result.size());
		assertEquals(application, result.get(0));
	}

	@Test
	public void testGetApplicationsByJobpost() {
		Application application = new Application();
		when(applicationRepository.getApplicationsByJobpost(anyLong())).thenReturn(Arrays.asList(application));

		List<Application> result = applicationServiceImpl.getApplicationsByJobpost(1L);

		assertEquals(1, result.size());
		assertEquals(application, result.get(0));
	}

	@Test
	public void testUpdateApplication() {
		Application application = new Application();
		Jobseeker jobseeker = new Jobseeker();
		Jobpost jobpost = new Jobpost();
		Employer employer = new Employer();
		jobpost.setEmployer(employer);
		application.setJobSeeker(jobseeker);
		application.setJobPost(jobpost);

		when(entityManager.merge(any(Jobseeker.class))).thenReturn(jobseeker);
		when(entityManager.merge(any(Jobpost.class))).thenReturn(jobpost);
		when(applicationRepository.save(any(Application.class))).thenReturn(application);

		Application result = applicationServiceImpl.updateApplication(application);

		assertEquals(application, result);
		verify(activityService, times(1)).addActivity(any(Activity.class));
	}
}