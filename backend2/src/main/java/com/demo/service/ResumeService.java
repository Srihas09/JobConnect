package com.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.demo.entites.Resume;


public interface ResumeService {
	
	Resume storeFile(MultipartFile file,int id);
	
	Resume getFileUpload(int id);


}
