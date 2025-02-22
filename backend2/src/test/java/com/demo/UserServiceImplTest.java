package com.demo.service;

import com.demo.entites.User;
import com.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllUsers() {
		User user = new User();
		when(userRepository.findAll()).thenReturn(Arrays.asList(user));

		List<User> result = userServiceImpl.getAllUsers();

		assertEquals(1, result.size());
		assertEquals(user, result.get(0));
	}

	@Test
	public void testAddUser() {
		User user = new User();
		when(userRepository.save(any(User.class))).thenReturn(user);

		User result = userServiceImpl.addUser(user);

		assertEquals(user, result);
	}

	@Test
	public void testUpdateUser() {
		User user = new User();
		when(userRepository.save(any(User.class))).thenReturn(user);

		User result = userServiceImpl.updateUser(user);

		assertEquals(user, result);
	}

	@Test
	public void testDeleteUser_Success() {
		User user = new User();
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

		Optional<User> result = userServiceImpl.deleteUser(1L);

		assertEquals(user, result.get());
		verify(userRepository, times(1)).deleteById(1L);
	}

	@Test
	public void testDeleteUser_NotFound() {
		when(userRepository.findById(anyLong())).thenReturn(null);

		assertThrows(Error.class, () -> userServiceImpl.deleteUser(1L));
	}
}