package com.example.ecommerce_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce_api.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
