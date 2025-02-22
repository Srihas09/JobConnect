package com.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.demo.entites.Activity;
import com.demo.entites.Jobpost;
import com.demo.repository.ActivityRepository;
@Service
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public Activity addActivity(Activity activity) {
		return activityRepository.save(activity);
	}

	@Override
	public List<Activity> getAllActivities() {
		return activityRepository.findAll();
	}

	@Override
	public Optional<Activity> deleteActivity(long activityId) {
		Optional<Activity> activity = activityRepository.findById(activityId);
		if(activity!=null)
		{
			activityRepository.deleteById(activityId);
			return activity;
		}
		else 
		{
			throw new Error("activity not found with given id");
		}
	}

	@Override
	public List<Activity> getActivityByJobseeker(long jobseekerId) {
		return activityRepository.getActivityByJobseeker(jobseekerId);
	}

	@Override
	public List<Activity> getActivityByEmployer(long employerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> getActivityByJobPost(long jobpostId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> getActivityByType(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
