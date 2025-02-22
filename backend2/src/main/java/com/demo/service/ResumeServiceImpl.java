package com.demo.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.entites.Resume;
import com.demo.repository.ResumeRepository;

@Service
public class ResumeServiceImpl implements ResumeService{
	
	@Autowired
	
	private ResumeRepository fileRepository;
	
	@Override
	public Resume storeFile(MultipartFile file,int id) {
		Resume f=new Resume();
		f.setFileName(file.getOriginalFilename());
		f.setFileType(file.getContentType());
		f.setJobseekerId(id);
		try {
			f.setData(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileRepository.save(f);
	}
 
	@Override
	public Resume getFileUpload(int id) {
		return fileRepository.findById(id).orElseThrow(()->new RuntimeException("failed"+id));
	}

}
