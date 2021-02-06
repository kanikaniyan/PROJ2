package com.alpha.Wellness_Backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.Wellness_Backend.model.User;
import com.alpha.Wellness_Backend.service.IUserService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/api")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@PostMapping("save-user")
	public boolean saveUser (@RequestBody User user) {
		return userService.addUser(user);
	}
	
	@GetMapping("user-list")
	public List<User> allUsers() {
		return userService.getAllUsers();
	}
	
	@DeleteMapping("delete-user/{user_id}")
	public boolean deleteUser(@PathVariable("user_id") int user_id) {
		return userService.deleteUser(user_id);
	}
	
	@GetMapping("user/{user_id}")
	public User userById(@PathVariable("user_id") int user_id) {
		return userService.getUserById(user_id);
	}
	
	@PostMapping("update-user/{user_id}")
	public boolean updateUser(@RequestBody User user, @PathVariable("user_id") int user_id) {
		user.setUserId(user_id);
		return userService.updateUser(user);
	}
}
