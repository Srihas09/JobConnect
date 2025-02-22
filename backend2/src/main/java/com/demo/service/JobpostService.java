package com.demo.service;

import java.util.List;
import java.util.Optional;

import com.demo.entites.Jobpost;

public interface JobpostService {
	
	public List<Jobpost> getByTitle(String title);//q
	public List<Jobpost> getBySalary(double salary);//q
	public List<Jobpost> getByLocation(String location);//q
	public List<Jobpost> sortByDate();//q
	public Jobpost addJobpost(Jobpost jobpost);
	public Jobpost updateJobpost(Jobpost jobpost);
	public Optional<Jobpost> deleteJobpost(long id);
	public Optional<Jobpost> getById(long id);
	
	

}
