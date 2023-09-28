package com.example.ecommerce_api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ecommerce_api.exception.ProductException;
import com.example.ecommerce_api.model.Product;
import com.example.ecommerce_api.model.Review;
import com.example.ecommerce_api.model.Users;
import com.example.ecommerce_api.repository.ProductRepository;
import com.example.ecommerce_api.repository.ReviewRepository;
import com.example.ecommerce_api.request.ReviewRequest;

@Service
public class ReviewServiceImplementation implements ReviewService{

	private ReviewRepository reviewRepository;
	private ProductService productService;
	private ProductRepository productRepository;
	
	
	public ReviewServiceImplementation(ReviewRepository reviewRepository, ProductService productService,
			ProductRepository productRepository) {
		
		this.reviewRepository = reviewRepository;
		this.productService = productService;
		this.productRepository = productRepository;
	}

	@Override
	public Review createReview(ReviewRequest req, Users userDto) throws ProductException {
	Product product=productService.findProductById(req.getProductId());
	Review review=new Review();
	review.setUsers(userDto);
	review.setProduct(product);
	review.setReview(req.getReview());
	review.setCreatedAt(LocalDateTime.now());
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long productId) {
		// TODO Auto-generated method stub
		return reviewRepository.getAllProductsReview(productId);
	}

}
