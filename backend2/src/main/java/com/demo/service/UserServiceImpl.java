package com.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entites.Activity;
import com.demo.entites.User;
import com.demo.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		List<User> userList = userRepository.findAll();
		System.out.println("users"+userList);
		return userList;
	}

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
		 
	}

	@Override
	public User updateUser(User user) {
		userRepository.save(user);
		return user;
	}

	@Override
	public Optional<User> deleteUser(long id) {
		Optional<User> isUser = userRepository.findById(id);
	
		if(isUser !=null) {			
			userRepository.deleteById(id);
			return isUser;
			
		}
		else {
			 throw new Error("User Not Found");
		}
		
		
	}
	

	

}
