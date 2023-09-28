package com.example.ecommerce_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.example.ecommerce_api.repository.UserRepository;
@Service
public class CustomeUserServiceImplementation implements UserDetailsService{

	private UserRepository userRepository;
	
	
	
	
	public CustomeUserServiceImplementation(UserRepository userRepository) {

		this.userRepository = userRepository;
	}




	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	com.example.ecommerce_api.model.Users userDto=userRepository.findByEmail(username);
	if(userDto==null) {
		throw new UsernameNotFoundException("user not found with email -"+username);	
	}
	
	List<GrantedAuthority> authorities=new ArrayList<>();
	
		return new User(userDto.getEmail(),userDto.getPassword(),authorities);
	}

}
