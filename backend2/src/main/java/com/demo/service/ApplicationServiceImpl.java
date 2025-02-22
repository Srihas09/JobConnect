package com.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entites.Activity;
import com.demo.entites.Application;
import com.demo.entites.Employer;
import com.demo.entites.Jobpost;
import com.demo.entites.Jobseeker;
import com.demo.repository.ApplicationRepository;
import com.demo.repository.JobpostRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class ApplicationServiceImpl implements ApplicationService {
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private EntityManager entityManager;

	@Transactional
	@Override
	public Application addApplication(Application application) {
		
		Jobseeker jobseeker = application.getJobSeeker();
		Jobpost jobpost = application.getJobPost();
		jobpost = entityManager.merge(jobpost);
		jobseeker = entityManager.merge(jobseeker);
		application.setJobSeeker(jobseeker);
		application.setJobPost(jobpost);
		Application application2 = applicationRepository.save(application);
		Activity activity = new Activity();
		activity.setActivityDate(new Date());
		activity.setActivityType("Application added");
		Jobpost jobpost2 = application2.getJobPost();
		Employer employer = jobpost2.getEmployer();
		Jobseeker jobseeker2 = application2.getJobSeeker();
		activity.setJobPostId(jobpost2.getJobPostId());
		activity.setEmployerId(employer.getEmployerId());
		activity.setJobSeekerId(jobseeker2.getJobSeekerId());
		activity.setDetails("When jobseeker applied");
		activityService.addActivity(activity);
		return application2;
	}

	@Override
	public List<Application> getAllApplications() {
		return applicationRepository.findAll();
	}

	@Override
	public List<Application> getApplicationsByJobseeker(long jobseekerId) {
		return applicationRepository.getApplicationsByJobseeker(jobseekerId);
	}

	@Override
	public List<Application> getApplicationsByJobpost(long jobpostId) {
		return applicationRepository.getApplicationsByJobpost(jobpostId);
	}

	@Override
	public Application updateApplication(Application application) {
		Jobseeker jobseeker = application.getJobSeeker();
		Jobpost jobpost = application.getJobPost();
		jobpost = entityManager.merge(jobpost);
		jobseeker = entityManager.merge(jobseeker);
		application.setJobSeeker(jobseeker);
		application.setJobPost(jobpost);
		Application application2 = applicationRepository.save(application);
		Activity activity = new Activity();
		activity.setActivityDate(new Date());
		activity.setActivityType("Application updated");
		Jobpost jobpost2 = application2.getJobPost();
		Employer employer = jobpost2.getEmployer();
		Jobseeker jobseeker2 = application2.getJobSeeker();
		activity.setJobPostId(jobpost2.getJobPostId());
		activity.setEmployerId(employer.getEmployerId());
		activity.setJobSeekerId(jobseeker2.getJobSeekerId());
		activity.setDetails("When jobseeker applied");
		activityService.addActivity(activity);
		return application2;
	}

}
