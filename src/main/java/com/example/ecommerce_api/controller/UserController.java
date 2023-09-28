package com.example.ecommerce_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce_api.exception.UserException;
import com.example.ecommerce_api.model.Users;
import com.example.ecommerce_api.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/profile")
	public ResponseEntity<Users> getUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException{
		Users userDto=userService.findUserProfileByJwt(jwt);
		return new ResponseEntity<>(userDto,HttpStatus.ACCEPTED);
	}
	
}
