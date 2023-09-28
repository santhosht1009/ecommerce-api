package com.example.ecommerce_api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ecommerce_api.exception.ProductException;
import com.example.ecommerce_api.model.Product;
import com.example.ecommerce_api.model.Rating;
import com.example.ecommerce_api.model.Users;
import com.example.ecommerce_api.repository.RatingRepository;
import com.example.ecommerce_api.request.RatingRequest;
@Service
public class RatingServiceImplementation implements RatingService{

	private RatingRepository ratingRepository;
	private ProductService productService;
	
	
	
	public RatingServiceImplementation(RatingRepository ratingRepository, ProductService productService) {
	
		this.ratingRepository = ratingRepository;
		this.productService = productService;
	}

	@Override
	public Rating createRating(RatingRequest req, Users userDto) throws ProductException {
		Product product=productService.findProductById(req.getProductId());
		Rating rating=new Rating();
		rating.setUsers(userDto);
		rating.setRating(req.getRating());
		rating.setCreatedAt(LocalDateTime.now());
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getProductsRating(Long productId) {
		// TODO Auto-generated method stub
		return ratingRepository.getAllProductsRating(productId);
	}

}
