package com.example.ecommerce_api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ecommerce_api.exception.CartItemException;
import com.example.ecommerce_api.exception.UserException;
import com.example.ecommerce_api.model.Cart;
import com.example.ecommerce_api.model.CartItem;
import com.example.ecommerce_api.model.Product;
import com.example.ecommerce_api.model.Users;
import com.example.ecommerce_api.repository.CartItemRepository;
import com.example.ecommerce_api.repository.CartRepository;

@Service
public class CartItemServiceImplementation implements CartItemService {

	private CartItemRepository cartItemRepository;
	private UserService userService;
	private CartRepository cartRepository;

	public CartItemServiceImplementation(CartItemRepository cartItemRepository, UserService userService,
			CartRepository cartRepository) {
	
		this.cartItemRepository = cartItemRepository;
		this.userService = userService;
		this.cartRepository = cartRepository;
	}

	@Override
	public CartItem createCartItem(CartItem cartItem) {
		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
		cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());

		CartItem createdCartItem=cartItemRepository.save(cartItem);
		
		return createdCartItem;
	}

	@Override
	public CartItem updateCartItem(Long usersId, Long id, CartItem cartItem) throws CartItemException, UserException {
		CartItem item=findCartItemById(id);
		Users userDto=userService.findUserById(item.getUsersId());
		if(userDto.getId().equals(usersId)) {
			item.setQuantity(cartItem.getQuantity());
			item.setPrice(item.getQuantity()*item.getProduct().getPrice());
			item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
		}
		return cartItemRepository.save(item);
	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long usersId) {
		CartItem cartItem=cartItemRepository.isCartItemExist(cart, product, size, usersId);
		return cartItem;
	}

	@Override
	public void removeCartItem(Long usersId, Long cartItemId) throws CartItemException, UserException {
		CartItem cartItem=findCartItemById(cartItemId);
		Users userDto=userService.findUserById(cartItem.getUsersId());
		Users reqUser=userService.findUserById(usersId);
		if(userDto.getId().equals(reqUser.getId())) {
			cartItemRepository.deleteById(cartItemId);
		}else
		{
			throw new UserException("you can't remove another users item");
		}
		
	}

	@Override
	public CartItem findCartItemById(Long cartItemId) throws CartItemException {
		Optional<CartItem> opt=cartItemRepository.findById(cartItemId);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new CartItemException("cartitem not found with id:"+cartItemId);
	}

}
