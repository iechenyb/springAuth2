package com.cyb.auth2.service;

import java.util.List;

import com.cyb.auth2.domain.User;

public interface UserService {
	public void saveUsers(List<User> us);
	public List<User> getAllUsers();
}
