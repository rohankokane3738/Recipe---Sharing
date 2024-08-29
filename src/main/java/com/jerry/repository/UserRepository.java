package com.jerry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jerry.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
	public User findByEmail(String email);
}
