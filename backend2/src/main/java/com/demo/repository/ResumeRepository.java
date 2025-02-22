package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entites.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {

}
