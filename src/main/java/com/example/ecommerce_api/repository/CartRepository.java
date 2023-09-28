package com.example.ecommerce_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ecommerce_api.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
@Query("SELECT c from Cart c where c.users.id=:usersId")
	public Cart findUserId(@Param("usersId") Long usersId);

}
