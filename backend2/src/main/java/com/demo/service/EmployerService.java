package com.demo.service;

import java.util.List;
import java.util.Optional;

import com.demo.entites.Employer;

public interface EmployerService {
	
	public Employer addEmployer(Employer employer);
	public List<Employer> getAllEmployers();
	public Optional<Employer> getEmployerById(long id);
	public Employer updateEmployer(Employer employer);
	public Optional<Employer> deleteEmployer(long id);

}
