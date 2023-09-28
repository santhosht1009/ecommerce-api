package com.example.ecommerce_api.model;

import org.springframework.stereotype.Component;

@Component
public class Size {
private String name;
public Size(String name, int quantity) {

	this.name = name;
	this.quantity = quantity;
}
private int quantity;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public Size() {
	// TODO Auto-generated constructor stub
}
	
	
}
