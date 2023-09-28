package com.example.ecommerce_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce_api.exception.ProductException;
import com.example.ecommerce_api.exception.UserException;
import com.example.ecommerce_api.model.Cart;
import com.example.ecommerce_api.model.Users;
import com.example.ecommerce_api.request.AddItemRequest;
import com.example.ecommerce_api.response.ApiResponse;
import com.example.ecommerce_api.service.CartService;
import com.example.ecommerce_api.service.UserService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
@Autowired
private CartService cartService;
@Autowired
private UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException{
		Users userDto=userService.findUserProfileByJwt(jwt);
		Cart cart=cartService.findUserCart(userDto.getId());
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
	}
	
	@PutMapping("/add")
	public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req,
			@RequestHeader("Authorization") String jwt) throws UserException,ProductException{
		Users userDto=userService.findUserProfileByJwt(jwt);
	
		cartService.addCartItem(userDto.getId(), req);
		
		ApiResponse res=new ApiResponse();
		res.setMessage("item added to cart");
		res.setStatus(true);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	
}
