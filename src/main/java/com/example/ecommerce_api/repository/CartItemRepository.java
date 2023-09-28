package com.example.ecommerce_api.repository;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ecommerce_api.model.Cart;
import com.example.ecommerce_api.model.CartItem;
import com.example.ecommerce_api.model.Product;


public interface CartItemRepository extends JpaRepository<CartItem,Long>{

	@Query("SELECT ci from CartItem ci where ci.cart=:cart and ci.product=:product and ci.size=:size and ci.usersId=:usersId")
	public CartItem isCartItemExist(@Param("cart") Cart cart,@Param("product") Product product,@Param("size") String size,@Param("usersId") Long usersId);
	
	
	@Query("SELECT cis from CartItem cis where cis.cart=:cart")
	public Set<CartItem> findCartItemByCartId(@Param("cart") Cart cart);
}
