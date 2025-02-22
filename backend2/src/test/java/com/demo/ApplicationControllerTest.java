package com.demo.controller;

import com.demo.entites.Application;
import com.demo.service.ApplicationService;
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


public class ApplicationControllerTest {

    @Mock
    private ApplicationService applicationService;

    @InjectMocks
    private ApplicationController applicationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddApplication() {
        Application application = new Application();
        when(applicationService.addApplication(any(Application.class))).thenReturn(application);

        ResponseEntity<Application> response = applicationController.addApplication(application);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(application, response.getBody());
    }

    @Test
    public void testGetAllApplications() {
        Application application = new Application();
        when(applicationService.getAllApplications()).thenReturn(Arrays.asList(application));

        ResponseEntity<List<Application>> response = applicationController.getAllApplications();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(application, response.getBody().get(0));
    }

    @Test
    public void testGetApplicationsByJobseeker() {
        Application application = new Application();
        when(applicationService.getApplicationsByJobseeker(1L)).thenReturn(Arrays.asList(application));

        ResponseEntity<List<Application>> response = applicationController.getApplicationsByJobseeker(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(application, response.getBody().get(0));
    }
    
    @Test
    public void testGetApplicationsByJobseeker_CC() {
        Application application = new Application();
        when(applicationService.getApplicationsByJobseeker(1L)).thenReturn(null);

        ResponseEntity<List<Application>> response = applicationController.getApplicationsByJobseeker(1L);

    }

    @Test
    public void testGetApplicationsByJobseeker_NotFound() {
        when(applicationService.getApplicationsByJobseeker(1L)).thenReturn(Arrays.asList());

        ResponseEntity<List<Application>> response = applicationController.getApplicationsByJobseeker(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetApplicationsByJobpost() {
        Application application = new Application();
        when(applicationService.getApplicationsByJobpost(1L)).thenReturn(Arrays.asList(application));

        ResponseEntity<List<Application>> response = applicationController.getApplicationsByJobpost(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(application, response.getBody().get(0));
    }
    
    @Test
    public void testGetApplicationsByJobpost_NUll() {
        Application application = new Application();
        when(applicationService.getApplicationsByJobpost(1L)).thenReturn(null);

        ResponseEntity<List<Application>> response = applicationController.getApplicationsByJobpost(1L);

    }

    @Test
    public void testGetApplicationsByJobpost_NotFound() {
        when(applicationService.getApplicationsByJobpost(1L)).thenReturn(Arrays.asList());

        ResponseEntity<List<Application>> response = applicationController.getApplicationsByJobpost(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdateApplication() {
        Application existingApplication = new Application();
        existingApplication.setApplicationId(1L);
        Application updatedApplication = new Application();
        updatedApplication.setApplicationId(1L);
        updatedApplication.setStatus("Updated");

        when(applicationService.getAllApplications()).thenReturn(Arrays.asList(existingApplication));
        when(applicationService.updateApplication(any(Application.class))).thenReturn(updatedApplication);

        ResponseEntity<?> response = applicationController.updateApplication(1L, updatedApplication);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedApplication, response.getBody());
    }

    @Test
    public void testUpdateApplication_NotFound() {
        Application updatedApplication = new Application();
        updatedApplication.setApplicationId(1L);
        updatedApplication.setStatus("Updated");

        when(applicationService.getAllApplications()).thenReturn(Arrays.asList());

        ResponseEntity<?> response = applicationController.updateApplication(1L, updatedApplication);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Application not updated", response.getBody());
    }
}