 package com.example.ecommerce_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce_api.exception.OrderException;
import com.example.ecommerce_api.exception.UserException;
import com.example.ecommerce_api.model.Address;
import com.example.ecommerce_api.model.Orders;
import com.example.ecommerce_api.model.Users;
import com.example.ecommerce_api.service.OrderService;
import com.example.ecommerce_api.service.UserService;
@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/")
	public ResponseEntity<Orders> createProduct(@RequestBody Address shippingAddress,@RequestHeader("Authorization") String jwt)throws UserException{
		
		Users users=userService.findUserProfileByJwt(jwt);
		Orders order=orderService.createOrder(users,shippingAddress);
		return new ResponseEntity<Orders>(order,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/user")
	public ResponseEntity<List<Orders>> usersOrderHistory(@RequestHeader("Authorization") String jwt) throws UserException{
		Users userDto=userService.findUserProfileByJwt(jwt);
		List<Orders> orderDtos=orderService.usersOrderHistory(userDto.getId());
		
	
		return new ResponseEntity<List<Orders>>(orderDtos,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Orders> findOrderById(@PathVariable("id") Long orderId,@RequestHeader("Authorization") String jwt) throws UserException, OrderException{
		Users userDto=userService.findUserProfileByJwt(jwt);
		Orders orderDto=orderService.findOrderById(orderId);
		
	
		return new ResponseEntity<>(orderDto,HttpStatus.ACCEPTED);
	}
	
}
