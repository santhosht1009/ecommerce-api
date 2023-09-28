package com.example.ecommerce_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce_api.config.JwtProvider;
import com.example.ecommerce_api.exception.UserException;
import com.example.ecommerce_api.model.Cart;
import com.example.ecommerce_api.model.Users;
import com.example.ecommerce_api.repository.UserRepository;
import com.example.ecommerce_api.request.LoginRequest;
import com.example.ecommerce_api.response.AuthResponse;
import com.example.ecommerce_api.service.CartService;
import com.example.ecommerce_api.service.CustomeUserServiceImplementation;

@RestController
@RequestMapping("/auth")
public class AuthController {
private UserRepository userRepository;
private JwtProvider jwtProvider;
private PasswordEncoder passwordEncoder;
private CustomeUserServiceImplementation customeUserService; 
private CartService cartService;
public AuthController(UserRepository userRepository,CustomeUserServiceImplementation customeUserService,PasswordEncoder passwordEncoder,JwtProvider jwtProvider,CartService cartService) {

	this.userRepository = userRepository;
	this.customeUserService=customeUserService;
	this.passwordEncoder=passwordEncoder;
	this.jwtProvider=jwtProvider;
	this.cartService=cartService;
}
@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody Users userDto) throws UserException{
		String email=userDto.getEmail();
		String password=userDto.getPassword();
		String firstName=userDto.getFirstName();
		String lastName=userDto.getLastName();
		
		Users isEmailExist=userRepository.findByEmail(email);
		if(isEmailExist!=null) {
			throw new UserException("Email is Already used with other Account");
			
		}
		
		Users createdUser=new Users();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFirstName(firstName);
		createdUser.setLastName(lastName);
		Users savedUser=userRepository.save(createdUser);
		Cart cart=cartService.createCart(savedUser);
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token=jwtProvider.generateToken(authentication);
		
		
		AuthResponse authResponse=new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("SignUp Success");
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
	}
	

@PostMapping("/signin")
public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest){
	String username=loginRequest.getEmail();
	String password=loginRequest.getPassword();
	Authentication authentication=authenticate(username,password);
	SecurityContextHolder.getContext().setAuthentication(authentication);
	
	String token=jwtProvider.generateToken(authentication);
	
	
	AuthResponse authResponse=new AuthResponse();
	authResponse.setJwt(token);
	authResponse.setMessage("SignIn Success");
	return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
}
private Authentication authenticate(String username, String password) {
UserDetails details=customeUserService.loadUserByUsername(username);

if(details==null) {
	throw new BadCredentialsException("invalid username");
}

if(!passwordEncoder.matches(password, details.getPassword())){
	throw new BadCredentialsException("invalid password");
}

	return new UsernamePasswordAuthenticationToken(details,null,details.getAuthorities());
}
	
}
