package com.alpha.Wellness_Backend.dao;

import java.util.List;

import com.alpha.Wellness_Backend.model.User;

public interface IUserDao {
	
	List<User> userListbyStatus(String status);
	List<User> getAllUsers();
	User getUserById(int userId);
	User getUserByUsername(String userName);
	User validateUser(User user);
	boolean addUser(User user);
	boolean updateUser(User user);
	boolean deleteUser(int userId);
	boolean deactivateUser(int userId);
	boolean updateUserProfile(String file, Integer userId);
}
