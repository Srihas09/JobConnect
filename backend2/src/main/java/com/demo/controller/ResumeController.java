package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.entites.Resume;
import com.demo.service.ResumeService;

@RestController
@RequestMapping("/resume")
@CrossOrigin("*")
public class ResumeController {
	
	@Autowired
	private ResumeService resumeService;
	
	
	@PostMapping("/upload/{jobseekerId}")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,@PathVariable int jobseekerId){
		try {
			
			Resume f=resumeService.storeFile(file,jobseekerId);
			
			 return ResponseEntity.ok("File uploaded successfully! ID: " + f.getResumeId());
		}catch(Exception e) {
			   return ResponseEntity.status(500).body("File upload failed");
		}
	}
	 @GetMapping("/{id}")
	    public ResponseEntity<byte[]> downloadFile(@PathVariable int id) {
	    	Resume wordFile = resumeService.getFileUpload(id);
 
	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(wordFile.getFileType()))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + wordFile.getFileName() + "\"")
	                .body(wordFile.getData());
	    }
	
	

}
