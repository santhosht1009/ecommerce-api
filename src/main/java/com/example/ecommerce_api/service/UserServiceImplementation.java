package com.example.ecommerce_api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;


import com.example.ecommerce_api.config.JwtProvider;
import com.example.ecommerce_api.exception.UserException;
import com.example.ecommerce_api.model.Users;
import com.example.ecommerce_api.repository.UserRepository;
@Service
public class UserServiceImplementation implements UserService {

	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	
	
	
	public UserServiceImplementation(UserRepository userRepository, JwtProvider jwtProvider) {
		
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
	}

	@Override
	public Users findUserById(Long userId) throws UserException {
	Optional<Users> user=userRepository.findById(userId);
	if(user.isPresent()) {
		return user.get();
	}
		throw new UserException("user not found with id:"+userId);
	}

	@Override
	public Users findUserProfileByJwt(String jwt) throws UserException {
		String email=jwtProvider.getEmailFromToken(jwt);
		Users userDto=userRepository.findByEmail(email);
		if(userDto==null) {
			throw new UserException("user not found with email:"+email);
		}
		return userDto;
	}

}
