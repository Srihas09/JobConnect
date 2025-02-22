package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.demo.entites.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application,Long> {
	@Query(value = "select * from application where jobseeker_id=:jobseekerId",nativeQuery = true)
	public List<Application> getApplicationsByJobseeker(long jobseekerId);//q
	@Query(value = "select * from application where jobpost_id=:jobpostId",nativeQuery = true)
	public List<Application> getApplicationsByJobpost(long jobpostId);//q

}
