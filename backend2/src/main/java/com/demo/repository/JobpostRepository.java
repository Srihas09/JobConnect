package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.entites.Jobpost;
@Repository
public interface JobpostRepository extends JpaRepository<Jobpost, Long> {
	
	@Query(value = "select * from jobpost where title=:title",nativeQuery = true)
	public List<Jobpost> getByTitle(String title);//q	
	@Query(value = "select * from jobpost where salary=:salary",nativeQuery = true)
	public List<Jobpost> getBySalary(double salary);//q
	@Query(value = "select * from jobpost where location=:location",nativeQuery = true)
	public List<Jobpost> getByLocation(String location);//q
	@Query(value="select * from jobpost order by posted_date",nativeQuery =true )
	public List<Jobpost> sortByDate();//q

}
