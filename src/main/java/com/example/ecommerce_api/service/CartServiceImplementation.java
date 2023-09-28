package com.example.ecommerce_api.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce_api.exception.ProductException;
import com.example.ecommerce_api.model.Cart;
import com.example.ecommerce_api.model.CartItem;
import com.example.ecommerce_api.model.Product;
import com.example.ecommerce_api.model.Users;
import com.example.ecommerce_api.repository.CartItemRepository;
import com.example.ecommerce_api.repository.CartRepository;
import com.example.ecommerce_api.request.AddItemRequest;

@Service
public class CartServiceImplementation implements CartService{

	private CartRepository cartRepository;
	private CartItemService cartItemService;
	private ProductService productService;
	
	
	
	public CartServiceImplementation(CartRepository cartRepository, CartItemService cartItemService,
			ProductService productService) {
		
		this.cartRepository = cartRepository;
		this.cartItemService = cartItemService;
		this.productService = productService;
	}

	@Override
	public Cart createCart(Users userDto) {
		Cart cart=new Cart();
		cart.setUsers(userDto);
		return cartRepository.save(cart);
	}

	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
		Cart cart=cartRepository.findUserId(userId);
		Product product=productService.findProductById(req.getProductId());
		CartItem isPresent=cartItemService.isCartItemExist(cart, product, req.getSize(), userId);
		if(isPresent==null) {
			CartItem cartItem=new CartItem();
			cartItem.setCart(cart);
			cartItem.setProduct(product);
			cartItem.setQuantity(req.getQuantity());
			cartItem.setUsersId(userId);
			
			int price=req.getQuantity()*product.getDiscountedPrice();
			cartItem.setPrice(price);
			cartItem.setSize(req.getSize());
			
			CartItem createdCartItem=cartItemService.createCartItem(cartItem);
			cart.getCartItems().add(createdCartItem);
			
		}
		
		return "item Add to Cart";
	}

	@Override
	public Cart findUserCart(Long usersId) {
		Cart cart=cartRepository.findUserId(usersId);
		int totalPrice=0;
		int totalDiscountedPrice=0;
		int totalItem=0;
		
		for(CartItem cartItem:cart.getCartItems()) {
		totalPrice=totalPrice+cartItem.getPrice();
			totalDiscountedPrice=totalDiscountedPrice+cartItem.getDiscountedPrice();
			totalItem=totalItem+cartItem.getQuantity();
		}
		
		cart.setTotalDiscountedPrice(totalDiscountedPrice);
cart.setTotalItem(totalItem);
cart.setTotalPrice(totalPrice);
cart.setDiscount(totalPrice-totalDiscountedPrice);


		
		return cartRepository.save(cart);
	}

}
