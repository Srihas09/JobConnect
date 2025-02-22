package com.demo.controller;

import com.demo.entites.Employer;
import com.demo.service.EmployerService;
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


public class EmployerControllerTest {

    @Mock
    private EmployerService employerService;

    @InjectMocks
    private EmployerController employerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddEmployer() {
        Employer employer = new Employer();
        when(employerService.addEmployer(any(Employer.class))).thenReturn(employer);

        ResponseEntity<Employer> response = employerController.addEmployer(employer);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employer, response.getBody());
    }

    @Test
    public void testGetAllEmployers() {
        Employer employer = new Employer();
        when(employerService.getAllEmployers()).thenReturn(Arrays.asList(employer));

        ResponseEntity<List<Employer>> response = employerController.getAllEmployers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(employer, response.getBody().get(0));
    }

    @Test
    public void testGetEmployerById() {
        Employer employer = new Employer();
        when(employerService.getEmployerById(1L)).thenReturn(Optional.of(employer));

        ResponseEntity<?> response = employerController.getEmployerById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employer, response.getBody());
    }

    @Test
    public void testGetEmployerById_NotFound() {
        when(employerService.getEmployerById(1L)).thenReturn(Optional.empty());

        ResponseEntity<?> response = employerController.getEmployerById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Employer not Found", response.getBody());
    }

    @Test
    public void testUpdateEmployer() {
        Employer existingEmployer = new Employer();
        existingEmployer.setEmployerId(1L);
        Employer updatedEmployer = new Employer();
        updatedEmployer.setEmployerId(1L);
        updatedEmployer.setCompanyName("Updated Company");

        when(employerService.getEmployerById(1L)).thenReturn(Optional.of(existingEmployer));
        when(employerService.updateEmployer(any(Employer.class))).thenReturn(updatedEmployer);

        ResponseEntity<?> response = employerController.updateEmployer(1L, updatedEmployer);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedEmployer, response.getBody());
    }

    @Test
    public void testUpdateEmployer_NotFound() {
        Employer updatedEmployer = new Employer();
        updatedEmployer.setEmployerId(1L);
        updatedEmployer.setCompanyName("Updated Company");

        when(employerService.getEmployerById(1L)).thenReturn(Optional.empty());

        ResponseEntity<?> response = employerController.updateEmployer(1L, updatedEmployer);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Employer not Updated.", response.getBody());
    }

    @Test
    public void testDeleteEmployer() {
        Employer employer = new Employer();
        when(employerService.deleteEmployer(1L)).thenReturn(Optional.of(employer));

        ResponseEntity<String> response = employerController.deleteEmployer(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Employer deleted successfully", response.getBody());
    }

    @Test
    public void testDeleteEmployer_NotFound() {
        when(employerService.deleteEmployer(1L)).thenReturn(Optional.empty());

        ResponseEntity<String> response = employerController.deleteEmployer(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Employer not found", response.getBody());
    }
}