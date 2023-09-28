package com.example.ecommerce_api.service;

import java.util.List;

import com.example.ecommerce_api.exception.OrderException;
import com.example.ecommerce_api.model.Address;
import com.example.ecommerce_api.model.Orders;
import com.example.ecommerce_api.model.Users;

public interface OrderService {

	public Orders createOrder(Users users,Address shippingAddress);
	public Orders findOrderById(Long orderId) throws OrderException;
	public List<Orders> usersOrderHistory(Long userId);
	public Orders placedOrder(Long orderId) throws OrderException;
	public Orders confirmedOrder(Long orderId) throws OrderException;
	public Orders shippedOrder(Long orderId) throws OrderException;
	public Orders deliveredOrder(Long orderId) throws OrderException;
	public Orders canceledOrder(Long orderId) throws OrderException;
	public List<Orders> getAllOrder();
	public void deleteOrder(Long orderId);
}
