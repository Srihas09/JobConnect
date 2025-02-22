
package com.demo.controller;

import com.demo.entites.Jobpost;
import com.demo.service.JobpostService;
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

public class JobpostControllerTest {

    @Mock
    private JobpostService jobpostService;

    @InjectMocks
    private JobpostController jobpostController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddJobpost() {
        Jobpost jobpost = new Jobpost();
        when(jobpostService.addJobpost(any(Jobpost.class))).thenReturn(jobpost);

        ResponseEntity<Jobpost> response = jobpostController.addJobpost(jobpost);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(jobpost, response.getBody());
    }

    @Test
    public void testUpdateJobpost() {
        Jobpost existingJobpost = new Jobpost();
        existingJobpost.setTitle("title");
        existingJobpost.setJobPostId(2l);
        Jobpost updatedJobpost = new Jobpost();
        updatedJobpost.setJobPostId(1l);
        updatedJobpost.setTitle("Updated Title");

        when(jobpostService.getById(1L)).thenReturn(Optional.of(existingJobpost));
        when(jobpostService.updateJobpost(any(Jobpost.class))).thenReturn(updatedJobpost);

        ResponseEntity<?> response = jobpostController.updateJobpost(1L, updatedJobpost);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedJobpost, response.getBody());
    }

    @Test
    public void testUpdateJobpost_NotFound() {
        Jobpost updatedJobpost = new Jobpost();
        updatedJobpost.setJobPostId(1l);
        updatedJobpost.setTitle("Updated Title");

        when(jobpostService.getById(1L)).thenReturn(Optional.empty());

        ResponseEntity<?> response = jobpostController.updateJobpost(1L, updatedJobpost);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Jobpost not Updated", response.getBody());
    }

    @Test
    public void testDeleteJobpost() {
        Jobpost jobpost = new Jobpost();
        when(jobpostService.deleteJobpost(1L)).thenReturn(Optional.of(jobpost));

        ResponseEntity<String> response = jobpostController.deleteJobpost(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Jobpost deleted successfully", response.getBody());
    }

    @Test
    public void testDeleteJobpost_NotFound() {
        when(jobpostService.deleteJobpost(1L)).thenReturn(Optional.empty());

        ResponseEntity<String> response = jobpostController.deleteJobpost(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Jobpost not found", response.getBody());
    }

    @Test
    public void testGetByTitle() {
        Jobpost jobpost = new Jobpost();
        when(jobpostService.getByTitle("title")).thenReturn(Arrays.asList(jobpost));

        ResponseEntity<List<Jobpost>> response = jobpostController.getByTitle("title");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(jobpost, response.getBody().get(0));
    }

    @Test
    public void testGetBySalary() {
        Jobpost jobpost = new Jobpost();
        when(jobpostService.getBySalary(50000)).thenReturn(Arrays.asList(jobpost));

        ResponseEntity<List<Jobpost>> response = jobpostController.getBySalary(50000);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(jobpost, response.getBody().get(0));
    }

    @Test
    public void testGetByLocation() {
        Jobpost jobpost = new Jobpost();
        when(jobpostService.getByLocation("location")).thenReturn(Arrays.asList(jobpost));

        ResponseEntity<List<Jobpost>> response = jobpostController.getByLocation("location");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(jobpost, response.getBody().get(0));
    }

    @Test
    public void testSortByDate() {
        Jobpost jobpost = new Jobpost();
        when(jobpostService.sortByDate()).thenReturn(Arrays.asList(jobpost));

        ResponseEntity<List<Jobpost>> response = jobpostController.sortByDate();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(jobpost, response.getBody().get(0));
    }
}