package com.example.ecommerce_api.service;

import com.example.ecommerce_api.exception.ProductException;
import com.example.ecommerce_api.model.Cart;
import com.example.ecommerce_api.model.Users;
import com.example.ecommerce_api.request.AddItemRequest;

public interface CartService {

	public Cart createCart(Users userDto);
	public String addCartItem(Long userId,AddItemRequest req) throws ProductException;
	public Cart findUserCart(Long usersId);
	
	
}
