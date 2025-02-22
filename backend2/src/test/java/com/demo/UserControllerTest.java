package com.demo.controller;

import com.demo.entites.User;
import com.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllUsers() {
		User user = new User();
		when(userService.getAllUsers()).thenReturn(Arrays.asList(user));

		List<User> response = userController.getAllUsers();

		assertEquals(1, response.size());
		assertEquals(user, response.get(0));
	}

	@Test
	public void testAddUser() {
		User user = new User();
		when(userService.addUser(any(User.class))).thenReturn(user);

		User response = userController.addUser(user);

		assertEquals(user, response);
	}

	@Test
	public void testUpdateUser() {
		User user = new User();
		when(userService.updateUser(any(User.class))).thenReturn(user);

		User response = userController.updateUser(user);

		assertEquals(user, response);
	}

	@Test
	public void testDeleteUser_Success() {
		User user = new User();
		when(userService.deleteUser(1L)).thenReturn(Optional.of(user));

		ResponseEntity<String> response = userController.deleteUser(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("User deleted successfully!", response.getBody());
	}

	@Test
	public void testDeleteUser_NotFound() {
		when(userService.deleteUser(1L)).thenReturn(Optional.empty());

		ResponseEntity<String> response = userController.deleteUser(1L);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("User not found!", response.getBody());
	}
}