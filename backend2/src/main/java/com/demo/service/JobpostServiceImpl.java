package com.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entites.Activity;
import com.demo.entites.Employer;
import com.demo.entites.Jobpost;
import com.demo.entites.Jobseeker;
import com.demo.repository.JobpostRepository;

@Service
public class JobpostServiceImpl implements JobpostService {
	
	@Autowired
	private JobpostRepository jobpostRepository;
	
	@Autowired
	private ActivityService activityService;

	@Override
	public List<Jobpost> getByTitle(String title) {
		return jobpostRepository.getByTitle(title);
	}

	@Override
	public List<Jobpost> getBySalary(double salary) {
		return jobpostRepository.getBySalary(salary);
	}

	@Override
	public List<Jobpost> getByLocation(String location) {
		return jobpostRepository.getByLocation(location);
	}

	@Override
	public List<Jobpost> sortByDate() {
		return jobpostRepository.sortByDate();
	}

	@Override
	public Jobpost addJobpost(Jobpost jobpost) {
		Jobpost jobpost2 = jobpostRepository.save(jobpost);
		Activity activity = new Activity();
		activity.setActivityDate(new Date());
		activity.setActivityType("jobpost added");
		Employer employer = jobpost.getEmployer();
		activity.setEmployerId(employer.getEmployerId());
		activity.setJobPostId(jobpost2.getJobPostId());
		activity.setJobSeekerId(0);
		activity.setDetails("by employer");
		activityService.addActivity(activity);
		
		return jobpost2;	}

	@Override
	public Jobpost updateJobpost(Jobpost jobpost) {
		Jobpost jobpost2 = jobpostRepository.save(jobpost);
		Activity activity = new Activity();
		activity.setActivityDate(new Date());
		activity.setActivityType("jobpost updated");
		Employer employer = jobpost.getEmployer();
		activity.setEmployerId(employer.getEmployerId());
		activity.setJobPostId(jobpost2.getJobPostId());
		activity.setJobSeekerId(0);
		activity.setDetails("by employer");
		activityService.addActivity(activity);
		
		return jobpost2;
	}

	@Override
	public Optional<Jobpost> deleteJobpost(long id) {
		Optional<Jobpost> jobpost = jobpostRepository.findById(id);
		if(jobpost!=null)
		{
			Activity activity = new Activity();
			activity.setActivityDate(new Date());
			activity.setActivityType("jobpost added");
			activity.setEmployerId(0);
			activity.setJobPostId(id);
			activity.setJobSeekerId(0);
			activity.setDetails("by employer");
			activityService.addActivity(activity);
			
			jobpostRepository.deleteById(id);
			return jobpost;
		}
		else 
		{
			throw new Error("jobpost not found with given id");
		}
	}

	@Override
	public Optional<Jobpost> getById(long id) {
		return jobpostRepository.findById(id);
	}

}
