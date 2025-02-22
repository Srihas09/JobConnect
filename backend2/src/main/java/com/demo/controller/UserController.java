package com.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entites.User;
import com.demo.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService us;
	
	@GetMapping("/getallusers")
	public List<User> getAllUsers() {
		return us.getAllUsers();
		
	}
	
	@PostMapping("/adduser")
	public User addUser(@RequestBody User user) {
		return us.addUser(user);
	}
	
	@PutMapping("/updateuser")
	public User updateUser(@RequestBody User user) {
		
		System.out.println(user.getUserId());
		
		return us.updateUser(user);
	}
	
	 @DeleteMapping("/deleteuser/{id}")
	 public ResponseEntity<String> deleteUser(@PathVariable long id) {
	     Optional<User> user = us.deleteUser(id);
	     if (user.isPresent()) {
	        return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
	     } else {
	        return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
	     }
	}
	
	
	
	

}
