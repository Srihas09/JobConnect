package com.demo.service;

import java.util.List;
import java.util.Optional;

import com.demo.entites.Activity;

public interface ActivityService {
	
	public Activity addActivity(Activity activity);
	public List<Activity> getAllActivities();
	public Optional<Activity> deleteActivity(long activityId);
	public List<Activity> getActivityByJobseeker(long jobseekerId);//q
	public List<Activity> getActivityByEmployer(long employerId);//q
	public List<Activity> getActivityByJobPost(long jobpostId);//q
	public List<Activity> getActivityByType(String type);//q

}
