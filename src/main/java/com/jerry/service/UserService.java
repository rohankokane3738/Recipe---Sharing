package com.jerry.service;

import com.jerry.model.User;

public interface UserService {

	public User findUserById(Long userId) throws Exception;
	
	public User findUserByJwt(String jwt) throws Exception;
}
