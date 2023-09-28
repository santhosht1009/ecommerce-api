package com.example.ecommerce_api.request;

import lombok.Data;

@Data
public class ReviewRequest {

	private Long productId;
	private String review;
	
	
}
