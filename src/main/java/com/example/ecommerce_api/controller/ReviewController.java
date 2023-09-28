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
import com.example.ecommerce_api.model.Review;
import com.example.ecommerce_api.model.Users;
import com.example.ecommerce_api.request.ReviewRequest;
import com.example.ecommerce_api.service.ReviewService;
import com.example.ecommerce_api.service.UserService;
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
	@Autowired
	private UserService userService;
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/create")
	public ResponseEntity<Review> createReview(@RequestBody ReviewRequest req,
			@RequestHeader("Authorization") String jwt)throws UserException, ProductException{
		
		Users userDto=userService.findUserProfileByJwt(jwt);
		
		Review review=reviewService.createReview(req, userDto);
		
		
		return new ResponseEntity<>(review,HttpStatus.CREATED);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Review>> getProductsReview(@PathVariable Long productId) throws Exception{
		
		List<Review> reviews=reviewService.getAllReview(productId);
		
	
		return new ResponseEntity<List<Review>>(reviews,HttpStatus.ACCEPTED);
	}
	
}
