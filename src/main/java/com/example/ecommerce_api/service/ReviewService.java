package com.example.ecommerce_api.service;

import java.util.List;

import com.example.ecommerce_api.exception.ProductException;
import com.example.ecommerce_api.model.Review;
import com.example.ecommerce_api.model.Users;
import com.example.ecommerce_api.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req,Users userDto)throws ProductException;
public List<Review> getAllReview(Long productId);




}
