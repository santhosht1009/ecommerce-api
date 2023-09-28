package com.example.ecommerce_api.service;

import java.util.List;

import com.example.ecommerce_api.exception.ProductException;
import com.example.ecommerce_api.model.Rating;
import com.example.ecommerce_api.model.Users;
import com.example.ecommerce_api.request.RatingRequest;

public interface RatingService {
public Rating createRating(RatingRequest req,Users userDto) throws ProductException;
public List<Rating> getProductsRating(Long productId);

	
	
	
}
