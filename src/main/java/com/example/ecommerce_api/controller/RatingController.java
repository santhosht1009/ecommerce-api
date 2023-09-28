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

import com.example.ecommerce_api.exception.ProductException;
import com.example.ecommerce_api.exception.UserException;
import com.example.ecommerce_api.model.Address;
import com.example.ecommerce_api.model.Orders;
import com.example.ecommerce_api.model.Rating;
import com.example.ecommerce_api.model.Users;
import com.example.ecommerce_api.request.RatingRequest;
import com.example.ecommerce_api.service.RatingService;
import com.example.ecommerce_api.service.UserService;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
	@Autowired
	private UserService userService;
	@Autowired
	private RatingService ratingService;
	
	
	@PostMapping("/create")
	public ResponseEntity<Rating> createRating(@RequestBody RatingRequest req,
			@RequestHeader("Authorization") String jwt)throws UserException, ProductException{
		
		Users userDto=userService.findUserProfileByJwt(jwt);
		
		Rating rating=ratingService.createRating(req, userDto);
		
		return new ResponseEntity<Rating>(rating,HttpStatus.CREATED);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Rating>> getProductsRating(@PathVariable Long productId, @RequestHeader("Authorization") String jwt) throws UserException{
		Users userDto=userService.findUserProfileByJwt(jwt);
		List<Rating> ratings=ratingService.getProductsRating(productId);
		
	
		return new ResponseEntity<List<Rating>>(ratings,HttpStatus.CREATED);
	}
}
