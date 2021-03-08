package com.alpha.Wellness_Backend.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alpha.Wellness_Backend.ImageRepo.ImageRepository;
import com.alpha.Wellness_Backend.model.Image;
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
	
	@PostMapping("validate-user")
	public User validateUser(@RequestBody User user) {
		return userService.validateUser(user);
	}
	
	@GetMapping("user-list")
	public List<User> allUsers() {
		return userService.getAllUsers();
	}
	
	@DeleteMapping("delete-user/{userId}")
	public boolean deleteUser(@PathVariable("userId") int user_id) {
		return userService.deleteUser(user_id);
	}
	
	@GetMapping("user/{userId}")
	public User userById(@PathVariable("userId") int user_id) {
		return userService.getUserById(user_id);
	}
	
	@PostMapping("update-user/{userId}")
	public boolean updateUser(@RequestBody User user, @PathVariable("userId") int user_id) {
		user.setUserId(user_id);
		return userService.updateUser(user);
	}
	
	@GetMapping("deactivate-list")
		public List<User> AllDeactivateUser() {
		return userService.getAllDeactivateUser();
	}
	
	@GetMapping("activate-list")
	public List<User> AllActivateUser() {
	return userService.getAllActiveUser();
	}
	
	@PostMapping("activate-user/{userId}")
	public boolean activateUser(@RequestBody User user, @PathVariable("userId") int user_id) {
		return userService.activateUser(user_id);
	}
	
	@PostMapping("deactivate-user/{userId}")
	public boolean deActivateUser(@RequestBody User user, @PathVariable("userId") int user_id) {
		return userService.deactivateUser(user_id);
	}
}
