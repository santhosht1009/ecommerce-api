package com.example.ecommerce_api.request;

import lombok.Data;

@Data
public class RatingRequest {

	private Long productId;
	private double rating;
	
	
}
