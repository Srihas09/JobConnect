package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.entites.Jobseeker;
@Repository
public interface JobseekerRepository extends JpaRepository<Jobseeker, Long> {
	
	@Query(value = "select j.city,j.jobseeker_email,j.jobseeker_id,j.jobseeker_name,j.pincode,j.state,j.street,j.jobseeker_phone from jobseeker j left join jobseeker_education je on j.jobseeker_id = je.jobseeker_jobseeker_id left join education e on je.education_education_id = e.education_id \r\n"
			+ "where e.degree=:degree",nativeQuery = true)
	public List<Jobseeker> getJobseekersByQualification(String degree);//q
	@Query(value = "select j.city,j.jobseeker_email,j.jobseeker_id,j.jobseeker_name,j.pincode,j.state,j.street,j.jobseeker_phone from jobseeker j left join jobseeker_experience je on j.jobseeker_id = je.jobseeker_jobseeker_id left join experience e on je.experience_experience_id = e.experience_id \r\n"
			+ "where e.experience_years=5;",nativeQuery = true)
	public List<Jobseeker> getJobseekerByExperience(int experience);//q

}
