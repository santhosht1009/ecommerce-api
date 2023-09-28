package com.example.ecommerce_api.service;

import com.example.ecommerce_api.exception.UserException;
import com.example.ecommerce_api.model.Users;

public interface UserService {

	public Users findUserById(Long userId) throws UserException;
	
	public Users findUserProfileByJwt(String jwt) throws UserException;
}
