package com.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entites.Activity;
import com.demo.entites.Address;
import com.demo.entites.Employer;
import com.demo.entites.Jobpost;
import com.demo.entites.User;
import com.demo.repository.EmployerRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class EmployerServiceImpl implements EmployerService {
	
	@Autowired
	private EmployerRepository employerRepository;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	@Override
	public Employer addEmployer(Employer employer) {
	    List<Jobpost> jobposts = employer.getJobPosts();
	    
	    // Check if jobposts is null or empty before processing
	    if (jobposts != null) {
	        for (int i = 0; i < jobposts.size(); i++) {
	            Jobpost jobpost = jobposts.get(i);
	            jobpost = entityManager.merge(jobpost);
	            jobposts.set(i, jobpost);
	        }
	        employer.setJobPosts(jobposts);
	    } else {
	        // Handle the case where jobposts is null, if necessary
	        System.out.println("jobposts is null, no job posts to process");
	    }

	    User user = employer.getUser();
	    user = entityManager.merge(user);
	    employer.setUser(user);

	    employer = entityManager.merge(employer);
	    Employer employer2 = employerRepository.save(employer);

	    Activity activity = new Activity();
	    activity.setActivityDate(new Date());
	    activity.setActivityType("Employer added");
	    activity.setEmployerId(employer2.getEmployerId());
	    activity.setJobPostId(0);
	    activity.setJobSeekerId(0);
	    activity.setDetails("through Employer registration form");
	    activityService.addActivity(activity);

	    return employerRepository.save(employer);
	}

	@Override
	public List<Employer> getAllEmployers() {
		return employerRepository.findAll();
	}

	@Override
	public Optional<Employer> getEmployerById(long id) {
		Optional<Employer> employer = employerRepository.findById(id);
		if(employer!=null)
		{
			return employer;
		}
		else 
		{
			throw new Error("employer not found with given id");
		}
	}

	@Override
	public Employer updateEmployer(Employer employer) {
		
		List<Jobpost> jobposts = employer.getJobPosts();
	    
	    // Check if jobposts is null or empty before processing
	    if (jobposts != null) {
	        for (int i = 0; i < jobposts.size(); i++) {
	            Jobpost jobpost = jobposts.get(i);
	            jobpost = entityManager.merge(jobpost);
	            jobposts.set(i, jobpost);
	        }
	        employer.setJobPosts(jobposts);
	    } else {
	        // Handle the case where jobposts is null, if necessary
	        System.out.println("jobposts is null, no job posts to process");
	    }

	    User user = employer.getUser();
	    user = entityManager.merge(user);
	    employer.setUser(user);

	    employer = entityManager.merge(employer);
	    Employer employer2 = employerRepository.save(employer);

	    Activity activity = new Activity();
	    activity.setActivityDate(new Date());
	    activity.setActivityType("Employer added");
	    activity.setEmployerId(employer2.getEmployerId());
	    activity.setJobPostId(0);
	    activity.setJobSeekerId(0);
	    activity.setDetails("through Employer registration form");
	    activityService.addActivity(activity);

	    return employerRepository.save(employer);
	}

	@Override
	public Optional<Employer> deleteEmployer(long id) {
		Optional<Employer> employer = employerRepository.findById(id);
		if(employer!=null)
		{
			Activity activity = new Activity();
			activity.setActivityDate(new Date());
			activity.setActivityType("Employer deleted");
			activity.setEmployerId(id);
			activity.setJobPostId(0);
			activity.setJobSeekerId(0);
			activity.setDetails("by admin");
			activityService.addActivity(activity);
			employerRepository.deleteById(id);
			return employer;
		}
		else 
		{
			throw new Error("employer not found with given id");
		}
	}

}
