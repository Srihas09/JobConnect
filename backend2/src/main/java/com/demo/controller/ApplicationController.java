package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.entites.Application;
import com.demo.service.ApplicationService;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    
    @PostMapping("/addapplication")
    public ResponseEntity<Application> addApplication(@RequestBody Application application) {
        Application addedApplication = applicationService.addApplication(application);
        return new ResponseEntity<>(addedApplication, HttpStatus.CREATED);
    }


    @GetMapping("/getallapplication")
    public ResponseEntity<List<Application>> getAllApplications() {
        List<Application> applications = applicationService.getAllApplications();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }


    @GetMapping("/getapplicationbyjobseeker/{jobseekerId}")
    public ResponseEntity<List<Application>> getApplicationsByJobseeker(@PathVariable long jobseekerId) {
        List<Application> applications = applicationService.getApplicationsByJobseeker(jobseekerId);
        if (applications != null && !applications.isEmpty()) {
            return new ResponseEntity<>(applications, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/getapplicationbyjobpost/{jobpostId}")
    public ResponseEntity<List<Application>> getApplicationsByJobpost(@PathVariable long jobpostId) {
        List<Application> applications = applicationService.getApplicationsByJobpost(jobpostId);
        if (applications != null && !applications.isEmpty()) {
            return new ResponseEntity<>(applications, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/updateapplication/{id}")
    public ResponseEntity<?> updateApplication(@PathVariable long id, @RequestBody Application applicationDetails) {
        Application existingApplication = applicationService.getAllApplications().stream()
                .filter(app -> app.getApplicationId() == id)
                .findFirst()
                .orElse(null);
        if (existingApplication != null) {
            existingApplication.setStatus(applicationDetails.getStatus());
            existingApplication.setAppliedDate(applicationDetails.getAppliedDate());
            existingApplication.setJobSeeker(applicationDetails.getJobSeeker());
            existingApplication.setJobPost(applicationDetails.getJobPost());

            Application updatedApplication = applicationService.updateApplication(existingApplication);
            return new ResponseEntity<Application>(updatedApplication, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Application not updated",HttpStatus.NOT_FOUND);
        }
    }

}
