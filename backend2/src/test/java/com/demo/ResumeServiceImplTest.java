package com.demo.service;

import com.demo.entites.Resume;
import com.demo.repository.ResumeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class ResumeServiceImplTest {

	@Mock
	private ResumeRepository fileRepository;

	@Mock
	private MultipartFile file;

	@InjectMocks
	private ResumeServiceImpl resumeServiceImpl;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testStoreFile_Success() throws IOException {
		Resume resume = new Resume();
		resume.setFileName("resume.pdf");
		resume.setFileType("application/pdf");
		resume.setJobseekerId(1);
		resume.setData(new byte[]{1, 2, 3});

		when(file.getOriginalFilename()).thenReturn("resume.pdf");
		when(file.getContentType()).thenReturn("application/pdf");
		when(file.getBytes()).thenReturn(new byte[]{1, 2, 3});
		when(fileRepository.save(any(Resume.class))).thenReturn(resume);

		Resume result = resumeServiceImpl.storeFile(file, 1);
	}

	@Test
	public void testStoreFile_IOException() throws IOException {
		when(file.getOriginalFilename()).thenReturn("resume.pdf");
		when(file.getContentType()).thenReturn("application/pdf");
		when(file.getBytes()).thenThrow(new IOException());

		Resume result = resumeServiceImpl.storeFile(file, 1);
	}

	@Test
	public void testGetFileUpload_Success() {
		Resume resume = new Resume();
		when(fileRepository.findById(anyInt())).thenReturn(Optional.of(resume));

		Resume result = resumeServiceImpl.getFileUpload(1);
	}

	@Test
	public void testGetFileUpload_NotFound() {
		when(fileRepository.findById(anyInt())).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> resumeServiceImpl.getFileUpload(1));
	}
}