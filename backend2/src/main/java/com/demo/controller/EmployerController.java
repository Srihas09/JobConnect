package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.entites.Employer;
import com.demo.service.EmployerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employer")
@CrossOrigin("*")
public class EmployerController {

    @Autowired
    private EmployerService es;

    @PostMapping("/addemployer")
    public ResponseEntity<Employer> addEmployer(@RequestBody Employer employer) {
        Employer addedEmployer = es.addEmployer(employer);
        return new ResponseEntity<Employer>(addedEmployer, HttpStatus.CREATED);
    }

    @GetMapping("/getallemployers")
    public ResponseEntity<List<Employer>> getAllEmployers() {
        List<Employer> employers = es.getAllEmployers();
        return new ResponseEntity<List<Employer>>(employers, HttpStatus.OK);
    }

    // Endpoint to get Employer by ID
    @GetMapping("/getemplyerbyid/{id}")
    public ResponseEntity<?> getEmployerById(@PathVariable long id) {
        Optional<Employer> employer = es.getEmployerById(id);
        if (employer.isPresent()) {
            return new ResponseEntity<Employer>(employer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Employer not Found",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateemployer/{id}")
    public ResponseEntity<?> updateEmployer(@PathVariable long id, @RequestBody Employer employerDetails) {
        Optional<Employer> employerOptional = es.getEmployerById(id);
        if (employerOptional.isPresent()) {
            Employer employer = employerOptional.get();
            employer.setCompanyName(employerDetails.getCompanyName());
            employer.setEmail(employerDetails.getEmail());
            employer.setPhone(employerDetails.getPhone());
            employer.setAddress(employerDetails.getAddress());
            employer.setJobPosts(employerDetails.getJobPosts());

            Employer updatedEmployer = es.updateEmployer(employer);
            return new ResponseEntity<Employer>(updatedEmployer, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Employer not Updated.",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("deleteemployer/{id}")
    public ResponseEntity<String> deleteEmployer(@PathVariable long id) {
        Optional<Employer> employer = es.deleteEmployer(id);
        if (employer.isPresent()) {
            return new ResponseEntity<String>("Employer deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Employer not found", HttpStatus.NOT_FOUND);
        }
    }
}
