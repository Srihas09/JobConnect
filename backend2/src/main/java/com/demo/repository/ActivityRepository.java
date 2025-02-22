package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entites.Activity;
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> { 
	
	@Query(value = "select * from activity where jobseeker_id=:jobseekerId",nativeQuery = true)
	public List<Activity> getActivityByJobseeker(@Param("jobseekerId")long  jobseekerId);
	@Query(value = "select * from activity where employer_id=:employerId",nativeQuery = true)
	public List<Activity> getActivityByEmployer(@Param("jobseekerId")long employerId);//q
	@Query(value = "select * from activity where job_post_id=:jobpostId",nativeQuery = true)
	public List<Activity> getActivityByJobPost(@Param("jobseekerId")long jobpostId);//q
	@Query(value = "select * from activity where activity_type=:type",nativeQuery = true)
	public List<Activity> getActivityByType(@Param("jobseekerId")String type);//q


}
