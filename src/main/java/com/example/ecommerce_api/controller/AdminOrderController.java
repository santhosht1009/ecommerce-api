package com.example.ecommerce_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce_api.exception.OrderException;
import com.example.ecommerce_api.model.Orders;
import com.example.ecommerce_api.response.ApiResponse;
import com.example.ecommerce_api.service.OrderService;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {
@Autowired
	OrderService orderService;

@GetMapping("/")
public ResponseEntity<List<Orders>> getAllOrdersHandler(){
	List<Orders> orders=orderService.getAllOrder();
	return new ResponseEntity<List<Orders>>(orders,HttpStatus.ACCEPTED);
}
@PutMapping("/{orderId}/confirmed")
public ResponseEntity<Orders> confirmedOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization") String jwt)throws OrderException{
	Orders orderDto=orderService.confirmedOrder(orderId);
	return new ResponseEntity<>(orderDto,HttpStatus.OK);
}

@PutMapping("/{orderId}/ship")
public ResponseEntity<Orders> shippedOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization") String jwt)throws OrderException{
	Orders orderDto=orderService.shippedOrder(orderId);
	return new ResponseEntity<>(orderDto,HttpStatus.OK);
}
	
@PutMapping("/{orderId}/deliver")
public ResponseEntity<Orders> deliverOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization") String jwt)throws OrderException{
	Orders orderDto=orderService.deliveredOrder(orderId);
	return new ResponseEntity<>(orderDto,HttpStatus.OK);
}

@PutMapping("/{orderId}/cancel")
public ResponseEntity<Orders> cancelOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization") String jwt)throws OrderException{
	Orders orderDto=orderService.canceledOrder(orderId);
	return new ResponseEntity<>(orderDto,HttpStatus.OK);
}
@DeleteMapping("/{orderId}/delete")
public ResponseEntity<ApiResponse> deleteOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization") String jwt)throws OrderException{
	
	orderService.deleteOrder(orderId);
	ApiResponse res=new ApiResponse();
	res.setMessage("order deleted successfully");
	res.setStatus(true);
	
	return new ResponseEntity<>(res,HttpStatus.OK);
}
}
