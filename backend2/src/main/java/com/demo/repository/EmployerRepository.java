package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entites.Employer;
@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long>{

}
