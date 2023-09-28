package com.example.ecommerce_api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ecommerce_api.exception.OrderException;
import com.example.ecommerce_api.model.Address;
import com.example.ecommerce_api.model.Cart;
import com.example.ecommerce_api.model.CartItem;
import com.example.ecommerce_api.model.OrderItem;
import com.example.ecommerce_api.model.Orders;
import com.example.ecommerce_api.model.Users;
import com.example.ecommerce_api.repository.AddressRepository;
import com.example.ecommerce_api.repository.CartRepository;
import com.example.ecommerce_api.repository.OrderItemRepository;
import com.example.ecommerce_api.repository.OrderRepository;
import com.example.ecommerce_api.repository.UserRepository;

@Service
public class OrderServiceImplementation implements OrderService{

private CartService cartService;
private OrderRepository orderRepository;
private AddressRepository addressRepository;
private OrderItemService orderItemService;
private OrderItemRepository orderItemRepository;
private UserRepository userRepository;

	




	public OrderServiceImplementation(CartService cartService, OrderRepository orderRepository,
		AddressRepository addressRepository, OrderItemService orderItemService, OrderItemRepository orderItemRepository,
		UserRepository userRepository) {
	super();
	this.cartService = cartService;
	this.orderRepository = orderRepository;
	this.addressRepository = addressRepository;
	this.orderItemService = orderItemService;
	this.orderItemRepository = orderItemRepository;
	this.userRepository = userRepository;
}

	@Override
	public Orders createOrder(Users users, Address shippingAddress) {
		shippingAddress.setUsers(users);
Address address=addressRepository.save(shippingAddress);
		users.getAddress().add(address);
		userRepository.save(users);
		
		Cart cart=cartService.findUserCart(users.getId());
		List<OrderItem> orderItems=new ArrayList<>();
		for(CartItem cartItem:cart.getCartItems())
		{
			OrderItem item=new OrderItem();
			item.setPrice(cartItem.getPrice());	
			item.setProduct(cartItem.getProduct());	
			item.setQuantity(cartItem.getQuantity());	
			item.setSize(cartItem.getSize());	
			item.setUserId(cartItem.getUsersId());	
			item.setDiscountedPrice(cartItem.getDiscountedPrice());	
		
			OrderItem createdOrderItem=orderItemRepository.save(item);
			orderItems.add(createdOrderItem);
			
		}
		
		Orders createdOrder=new Orders();
		createdOrder.setUsers(users);
		createdOrder.setOrderItems(orderItems);
		createdOrder.setTotalPrice(cart.getTotalPrice());
		createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
		createdOrder.setDiscount(cart.getDiscount());
		createdOrder.setTotalItem(cart.getTotalItem());;
		createdOrder.setShippingAddress(address);
		createdOrder.setOrderDate(LocalDateTime.now());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.getPaymentDetails().setStatus("PENDING");;
		createdOrder.setCreateAt(LocalDateTime.now());
		
		Orders savedOrder=orderRepository.save(createdOrder);
		for(OrderItem item:orderItems)
		{
			item.setOrder(savedOrder);
			orderItemRepository.save(item);
		}
		return savedOrder;
	}

	@Override
	public Orders findOrderById(Long orderId) throws OrderException {
		Optional<Orders> opt=orderRepository.findById(orderId);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new OrderException("order not exist with id"+orderId);
	}

	@Override
	public List<Orders> usersOrderHistory(Long userId) {
		List<Orders> orders=orderRepository.getUsersOrders(userId);
		
		return orders;
	}

	@Override
	public Orders placedOrder(Long orderId) throws OrderException {
		Orders orderDto=findOrderById(orderId);
		orderDto.setOrderStatus("PLACED");
		return orderRepository.save(orderDto);
	}

	@Override
	public Orders confirmedOrder(Long orderId) throws OrderException {
		Orders orderDto=findOrderById(orderId);
		orderDto.setOrderStatus("CONFIRMED");
		return orderRepository.save(orderDto);
	}

	@Override
	public Orders shippedOrder(Long orderId) throws OrderException {
		Orders orderDto=findOrderById(orderId);
		orderDto.setOrderStatus("SHIPPED");
		return orderRepository.save(orderDto);
	}

	@Override
	public Orders deliveredOrder(Long orderId) throws OrderException {
		Orders orderDto=findOrderById(orderId);
		orderDto.setOrderStatus("DELIVERED");
		return orderRepository.save(orderDto);
	}

	@Override
	public Orders canceledOrder(Long orderId) throws OrderException {
		Orders orderDto=findOrderById(orderId);
		orderDto.setOrderStatus("CANCELLED");
		return orderRepository.save(orderDto);
	}

	@Override
	public List<Orders> getAllOrder() {
		
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Long orderId) {
		
		orderRepository.deleteById(orderId);
		
	}

}
