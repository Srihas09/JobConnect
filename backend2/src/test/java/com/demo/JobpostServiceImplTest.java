package com.demo.service;

import com.demo.entites.Activity;
import com.demo.entites.Employer;
import com.demo.entites.Jobpost;
import com.demo.repository.JobpostRepository;
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


public class JobpostServiceImplTest {

	@Mock
	private JobpostRepository jobpostRepository;

	@Mock
	private ActivityService activityService;

	@InjectMocks
	private JobpostServiceImpl jobpostServiceImpl;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetByTitle() {
		Jobpost jobpost = new Jobpost();
		when(jobpostRepository.getByTitle(anyString())).thenReturn(Arrays.asList(jobpost));

		List<Jobpost> result = jobpostServiceImpl.getByTitle("title");

		assertEquals(1, result.size());
		assertEquals(jobpost, result.get(0));
	}

	@Test
	public void testGetBySalary() {
		Jobpost jobpost = new Jobpost();
		when(jobpostRepository.getBySalary(anyDouble())).thenReturn(Arrays.asList(jobpost));

		List<Jobpost> result = jobpostServiceImpl.getBySalary(50000);

		assertEquals(1, result.size());
		assertEquals(jobpost, result.get(0));
	}

	@Test
	public void testGetByLocation() {
		Jobpost jobpost = new Jobpost();
		when(jobpostRepository.getByLocation(anyString())).thenReturn(Arrays.asList(jobpost));

		List<Jobpost> result = jobpostServiceImpl.getByLocation("location");

		assertEquals(1, result.size());
		assertEquals(jobpost, result.get(0));
	}

	@Test
	public void testSortByDate() {
		Jobpost jobpost = new Jobpost();
		when(jobpostRepository.sortByDate()).thenReturn(Arrays.asList(jobpost));

		List<Jobpost> result = jobpostServiceImpl.sortByDate();

		assertEquals(1, result.size());
		assertEquals(jobpost, result.get(0));
	}

	@Test
	public void testAddJobpost() {
		Jobpost jobpost = new Jobpost();
		Employer employer = new Employer();
		employer.setEmployerId(1L);
		jobpost.setEmployer(employer);

		when(jobpostRepository.save(any(Jobpost.class))).thenReturn(jobpost);

		Jobpost result = jobpostServiceImpl.addJobpost(jobpost);

		assertEquals(jobpost, result);
		verify(activityService, times(1)).addActivity(any(Activity.class));
	}

	@Test
	public void testUpdateJobpost() {
		Jobpost jobpost = new Jobpost();
		Employer employer = new Employer();
		employer.setEmployerId(1L);
		jobpost.setEmployer(employer);

		when(jobpostRepository.save(any(Jobpost.class))).thenReturn(jobpost);

		Jobpost result = jobpostServiceImpl.updateJobpost(jobpost);

		assertEquals(jobpost, result);
		verify(activityService, times(1)).addActivity(any(Activity.class));
	}

	@Test
	public void testDeleteJobpost_Success() {
		Jobpost jobpost = new Jobpost();
		when(jobpostRepository.findById(anyLong())).thenReturn(Optional.of(jobpost));

		Optional<Jobpost> result = jobpostServiceImpl.deleteJobpost(1L);

		assertEquals(jobpost, result.get());
		verify(activityService, times(1)).addActivity(any(Activity.class));
		verify(jobpostRepository, times(1)).deleteById(1L);
	}

	@Test
	public void testDeleteJobpost_NotFound() {
		when(jobpostRepository.findById(anyLong())).thenReturn(null);

		assertThrows(Error.class, () -> jobpostServiceImpl.deleteJobpost(1L));
	}

	@Test
	public void testGetById_Success() {
		Jobpost jobpost = new Jobpost();
		when(jobpostRepository.findById(anyLong())).thenReturn(Optional.of(jobpost));

		Optional<Jobpost> result = jobpostServiceImpl.getById(1L);

		assertEquals(jobpost, result.get());
	}

	@Test
	public void testGetById_NotFound() {
		when(jobpostRepository.findById(anyLong())).thenReturn(Optional.empty());

		Optional<Jobpost> result = jobpostServiceImpl.getById(1L);

		assertEquals(Optional.empty(), result);
	}
}