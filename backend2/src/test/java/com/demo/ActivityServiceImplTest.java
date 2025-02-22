
package com.demo.service;

import com.demo.entites.Activity;
import com.demo.repository.ActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


public class ActivityServiceImplTest {

	@Mock
	private ActivityRepository activityRepository;

	@InjectMocks
	private ActivityServiceImpl activityServiceImpl;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAddActivity() {
		Activity activity = new Activity();
		when(activityRepository.save(any(Activity.class))).thenReturn(activity);

		Activity result = activityServiceImpl.addActivity(activity);

		assertEquals(activity, result);
	}

	@Test
	public void testGetAllActivities() {
		Activity activity = new Activity();
		when(activityRepository.findAll()).thenReturn(Arrays.asList(activity));

		List<Activity> result = activityServiceImpl.getAllActivities();

		assertEquals(1, result.size());
		assertEquals(activity, result.get(0));
	}

	@Test
	public void testDeleteActivity_Success() {
		Activity activity = new Activity();
		when(activityRepository.findById(anyLong())).thenReturn(Optional.of(activity));

		Optional<Activity> result = activityServiceImpl.deleteActivity(1L);

		assertEquals(activity, result.get());
		verify(activityRepository, times(1)).deleteById(1L);
	}

	@Test
	public void testDeleteActivity_NotFound() {
		when(activityRepository.findById(anyLong())).thenReturn(null);

		assertThrows(Error.class, () -> activityServiceImpl.deleteActivity(1l));
	}

	@Test
	public void testGetActivityByJobseeker() {
		Activity activity = new Activity();
		when(activityRepository.getActivityByJobseeker(anyLong())).thenReturn(Arrays.asList(activity));

		List<Activity> result = activityServiceImpl.getActivityByJobseeker(1L);

		assertEquals(1, result.size());
		assertEquals(activity, result.get(0));
	}

	@Test
	public void testGetActivityByEmployer() {
		Activity activity = new Activity();
		when(activityRepository.getActivityByEmployer(anyLong())).thenReturn(Arrays.asList(activity));

		List<Activity> result = activityServiceImpl.getActivityByEmployer(1L);
	}

	@Test
	public void testGetActivityByJobPost() {
		Activity activity = new Activity();
		when(activityRepository.getActivityByJobPost(anyLong())).thenReturn(Arrays.asList(activity));

		List<Activity> result = activityServiceImpl.getActivityByJobPost(1L);
	}

	@Test
	public void testGetActivityByType() {
		Activity activity = new Activity();
		when(activityRepository.getActivityByType(anyString())).thenReturn(Arrays.asList(activity));

		List<Activity> result = activityServiceImpl.getActivityByType("type");
	}
}