package com.example.ecommerce_api.service;


import org.springframework.stereotype.Service;

import com.example.ecommerce_api.model.OrderItem;
import com.example.ecommerce_api.repository.OrderItemRepository;
@Service
public class OrderItemServiceImplementation implements OrderItemService{

	private OrderItemRepository itemRepository;
	
	public OrderItemServiceImplementation(OrderItemRepository itemRepository) {
		
		this.itemRepository = itemRepository;
	}

	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		
		return itemRepository.save(orderItem);
	}

}
