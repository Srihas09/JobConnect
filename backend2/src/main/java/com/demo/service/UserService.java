package com.demo.service;

import java.util.List;
import java.util.Optional;

import com.demo.entites.User;

public interface UserService {
	
	
	public List<User> getAllUsers();
	public User addUser(User user);
	public User updateUser(User user);
	public Optional<User> deleteUser(long id);
	
}
