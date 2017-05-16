package com.cyb.auth2.repo;

import java.util.List;

import com.cyb.auth2.domain.User;

public interface UserDao {
	public int save(User u);
	public List<User> findAll();
}
