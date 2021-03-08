package com.alpha.Wellness_Backend.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.Wellness_Backend.model.Image;
import com.alpha.Wellness_Backend.model.User;

public interface IUserService {
	
	List<User> userListbyStatus(String status);
	List<User> getAllUsers();
	User getUserById(int userId);
	User getUserByUsername(String userName);
	User validateUser(User user);
	boolean addUser(User user);
	boolean updateUser(User user);
	boolean deleteUser(int userId);
	boolean deactivateUser(int userId);
	boolean activateUser(int userId);
	List<User> getAllDeactivateUser();
	boolean updateUserProfile(String file, Integer userId);
	List<User> getAllActiveUser();
	boolean logoutUser(int userId);
}