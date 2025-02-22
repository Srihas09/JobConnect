package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entites.Jobseeker;
import com.demo.service.JobseekerService;

@RestController
@RequestMapping("/jobseeker")
@CrossOrigin("*")
public class JobseekerController {
	
	@Autowired
	JobseekerService jobseekerService;
	
	@GetMapping("/getalljobseekers")
	public List<Jobseeker> getAllJobseekers() {
		return jobseekerService.getAllJobseekers();
	}
	
	@GetMapping("/getjobseeker/{id}")
	public ResponseEntity<Jobseeker> getJobseekerById(@PathVariable long id) {
		return jobseekerService.getJobseekerById(id);
	}
	
	@PostMapping("/addjobseeker")
	public ResponseEntity<Jobseeker> createJobseeker(@RequestBody Jobseeker jobseeker) {
	    Jobseeker savedJobseeker = jobseekerService.addJobseeker(jobseeker);  // JPA automatically generates jobseekerId
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedJobseeker);
	}
	
	@PutMapping("/updatejobseeker")
	public Jobseeker updateJobseeker(Jobseeker jobseeker) {
		return jobseekerService.updateJobseeker(jobseeker);
	}
	
	@DeleteMapping("/deletejobseeker/{id}")
	public ResponseEntity<String> deleteJobseeker(@PathVariable long id) {
		return jobseekerService.deleteJobseeker(id);
	}
	
	@GetMapping("/getjobseekerbyqualification/{degree}")
	public List<Jobseeker> getJobseekerByQualification(@PathVariable String degree) {
		return jobseekerService.getJobseekersByQualification(degree);
	}
	
	@GetMapping("/getjobseekerbyexperience/{years}")
	public List<Jobseeker> getJobseekerByExperience(@PathVariable int years) {
		return jobseekerService.getJobseekerByExperience(years);
	}
	

}
