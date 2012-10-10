package com.github.petstore.dao;

import java.util.List;

import com.github.petstore.entity.User;

public interface UserDao {
	User getUserById(String userId);

	User getAuthenticatedUser(String userId, String password);

	List<String> getUserIdList();

	void insertUser(User user);

	void updateUser(User user);
}
