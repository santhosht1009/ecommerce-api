package com.example.ecommerce_api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce_api.exception.CartItemException;
import com.example.ecommerce_api.exception.UserException;
import com.example.ecommerce_api.model.CartItem;
import com.example.ecommerce_api.model.Users;
import com.example.ecommerce_api.response.ApiResponse;
import com.example.ecommerce_api.service.CartItemService;
import com.example.ecommerce_api.service.UserService;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {
@Autowired
	CartItemService cartItemService;
	@Autowired
	UserService userService;
	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,@RequestHeader("Authorization") String jwt) throws UserException, CartItemException{
		Users users=userService.findUserProfileByJwt(jwt);
		cartItemService.removeCartItem(users.getId(), cartItemId);
	ApiResponse res=new ApiResponse();
	res.setMessage("item deleted from cart");
	res.setStatus(true);
	return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
	
	}
	
	
	@PutMapping("/{cartItemId}")
	public ResponseEntity<CartItem> updateCartItem(
			@RequestBody CartItem cartItem,
			@PathVariable Long cartItemId,@RequestHeader("Authorization") String jwt) throws UserException, CartItemException{
		Users users=userService.findUserProfileByJwt(jwt);
	CartItem updatedCartItem=	cartItemService.updateCartItem(users.getId(), cartItemId, cartItem);
	
	return new ResponseEntity<CartItem>(updatedCartItem,HttpStatus.OK);
	
	}
	
}
