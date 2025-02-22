package com.demo.controller;

import com.demo.entites.Activity;
import com.demo.service.ActivityService;
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


public class ActivityControllerTest {

    @Mock
    private ActivityService activityService;

    @InjectMocks
    private ActivityController activityController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddActivity() {
        Activity activity = new Activity();
        when(activityService.addActivity(any(Activity.class))).thenReturn(activity);

        ResponseEntity<Activity> response = activityController.addActivity(activity);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(activity, response.getBody());
    }

    @Test
    public void testGetAllActivities() {
        Activity activity = new Activity();
        when(activityService.getAllActivities()).thenReturn(Arrays.asList(activity));

        ResponseEntity<List<Activity>> response = activityController.getAllActivities();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(activity, response.getBody().get(0));
    }

    @Test
    public void testGetActivityByJobseeker() {
        Activity activity = new Activity();
        when(activityService.getActivityByJobseeker(1L)).thenReturn(Arrays.asList(activity));

        ResponseEntity<List<Activity>> response = activityController.getActivityByJobseeker(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(activity, response.getBody().get(0));
    }

    @Test
    public void testGetActivityByJobseeker_CC() {
        Activity activity = new Activity();
        when(activityService.getActivityByJobseeker(1L)).thenReturn(Arrays.asList());

        ResponseEntity<List<Activity>> response = activityController.getActivityByJobseeker(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetActivityByJobseeker_activityNull() {
        Activity activity = new Activity();
        when(activityService.getActivityByJobseeker(1L)).thenReturn(null);

        ResponseEntity<List<Activity>> response = activityController.getActivityByJobseeker(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetActivityByEmployer() {
        Activity activity = new Activity();
        when(activityService.getActivityByEmployer(1L)).thenReturn(Arrays.asList(activity));

        ResponseEntity<List<Activity>> response = activityController.getActivityByEmployer(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(activity, response.getBody().get(0));
    }

    @Test
    public void testGetActivityByEmployer_NullActivity() {
        Activity activity = new Activity();
        when(activityService.getActivityByEmployer(1L)).thenReturn(null);

        ResponseEntity<List<Activity>> response = activityController.getActivityByEmployer(1L);

    }

    @Test
    public void testGetActivityByEmployer_ActivityEmpty() {
        when(activityService.getActivityByEmployer(1L)).thenReturn(List.of());

        ResponseEntity<List<Activity>> response = activityController.getActivityByEmployer(1L);

    }

    @Test
    public void testGetActivityByJobPost() {
        Activity activity = new Activity();
        when(activityService.getActivityByJobPost(1L)).thenReturn(Arrays.asList(activity));

        ResponseEntity<List<Activity>> response = activityController.getActivityByJobPost(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(activity, response.getBody().get(0));
    }

    @Test
    public void testGetActivityByJobPost_emptyActivity() {
        Activity activity = new Activity();
        when(activityService.getActivityByJobPost(1L)).thenReturn(Arrays.asList());

        ResponseEntity<List<Activity>> response = activityController.getActivityByJobPost(1L);
    }

    @Test
    public void testGetActivityByJobPost_NullAct() {
        Activity activity = new Activity();
        when(activityService.getActivityByJobPost(1L)).thenReturn(null);

        ResponseEntity<List<Activity>> response = activityController.getActivityByJobPost(1L);
    }

    @Test
    public void testGetActivityByType() {
        Activity activity = new Activity();
        when(activityService.getActivityByType("type")).thenReturn(Arrays.asList(activity));

        ResponseEntity<List<Activity>> response = activityController.getActivityByType("type");
    }
    
    @Test
    public void testGetActivityByType_Null() {
        Activity activity = new Activity();
        when(activityService.getActivityByType("type")).thenReturn(null);

        ResponseEntity<List<Activity>> response = activityController.getActivityByType("type");
    }
    
    @Test
    public void testGetActivityByType_emptyList() {
        Activity activity = new Activity();
        when(activityService.getActivityByType("type")).thenReturn(Arrays.asList());

        ResponseEntity<List<Activity>> response = activityController.getActivityByType("type");
    }

    @Test
    public void testDeleteActivity() {
        Activity activity = new Activity();
        when(activityService.deleteActivity(1L)).thenReturn(Optional.of(activity));

        ResponseEntity<Activity> response = activityController.deleteActivity(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
    
    @Test
    public void testDeleteActivity_empty() {
        Activity activity = new Activity();
        when(activityService.deleteActivity(1L)).thenReturn(Optional.empty());

        ResponseEntity<Activity> response = activityController.deleteActivity(1L);
    }
}