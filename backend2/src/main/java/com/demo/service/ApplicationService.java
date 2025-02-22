package com.demo.service;

import java.util.List;

import com.demo.entites.Application;

public interface ApplicationService {
	
	public Application addApplication(Application application);
	public Application updateApplication(Application application);
	public List<Application> getAllApplications();
	public List<Application> getApplicationsByJobseeker(long jobseekerId);//q
	public List<Application> getApplicationsByJobpost(long jobpostId);//q
	
	

}
