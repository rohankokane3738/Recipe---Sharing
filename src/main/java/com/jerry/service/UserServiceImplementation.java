package com.jerry.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerry.config.JwtProvider;
import com.jerry.model.User;
import com.jerry.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public User findUserById(Long userId) throws Exception {
		
		Optional<User> opt = userRepository.findById(userId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new Exception("User not found with this id" +userId);
	}

	@Override
	public User findUserByJwt(String jwt) throws Exception {
		
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		
		if(email == null) {
			
			throw new Exception("provide valid JWT token...");
			
		}
		
		User user = userRepository.findByEmail(email);
			
			if(user == null) {
				
				throw new Exception("user not found email" +email);
			}
			
			return user;
		
		
		
	}
}
