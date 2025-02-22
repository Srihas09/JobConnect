package com.demo.controller;

import com.demo.entites.Resume;
import com.demo.service.ResumeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ResumeControllerTest {

	@Mock
	private ResumeService resumeService;

	@Mock
	private MultipartFile file;

	@InjectMocks
	private ResumeController resumeController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testUploadFile_Success() {
		Resume resume = new Resume();
		resume.setResumeId(1);
		when(resumeService.storeFile(any(MultipartFile.class), any(Integer.class))).thenReturn(resume);

		ResponseEntity<?> response = resumeController.uploadFile(file, 1);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("File uploaded successfully! ID: 1", response.getBody());
	}

	@Test
	public void testUploadFile_Failure() {
		when(resumeService.storeFile(any(MultipartFile.class), any(Integer.class))).thenThrow(new RuntimeException());

		ResponseEntity<?> response = resumeController.uploadFile(file, 1);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("File upload failed", response.getBody());
	}

	@Test
	public void testDownloadFile_Success() {
		Resume resume = new Resume();
		resume.setFileType("application/pdf");
		resume.setFileName("resume.pdf");
		resume.setData(new byte[]{1, 2, 3});
		when(resumeService.getFileUpload(1)).thenReturn(resume);

		ResponseEntity<byte[]> response = resumeController.downloadFile(1);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.parseMediaType("application/pdf"), response.getHeaders().getContentType());
		assertEquals("attachment; filename=\"resume.pdf\"", response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION));
		assertEquals(resume.getData(), response.getBody());
	}

	@Test
	public void testDownloadFile_NotFound() {
		when(resumeService.getFileUpload(1)).thenReturn(null);

		assertThrows(NullPointerException.class,()->{
			ResponseEntity<byte[]> response = resumeController.downloadFile(1);
		});
	}
}