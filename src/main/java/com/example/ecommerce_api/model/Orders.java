package com.example.ecommerce_api.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
public class Orders {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "order_id")
private String orderId;	
@ManyToOne
	private Users users;
@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
private List<OrderItem> orderItems=new ArrayList<>();
private LocalDateTime orderDate;
private LocalDateTime deliveryDate;
@OneToOne
private Address shippingAddress;
@Embedded
private PaymentDetails paymentDetails=new PaymentDetails();
private double totalPrice;
private Integer totalDiscountedPrice;
private Integer discount;
private String orderStatus;
private int totalItem;
private LocalDateTime createAt;
@Override
public String toString() {
	return "Orders [id=" + id + ", orderId=" + orderId + ", users=" + users + ", orderItems=" + orderItems
			+ ", orderDate=" + orderDate + ", deliveryDate=" + deliveryDate + ", shippingAddress=" + shippingAddress
			+ ", paymentDetails=" + paymentDetails + ", totalPrice=" + totalPrice + ", totalDiscountedPrice="
			+ totalDiscountedPrice + ", discount=" + discount + ", orderStatus=" + orderStatus + ", totalItem="
			+ totalItem + ", createAt=" + createAt + "]";
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getOrderId() {
	return orderId;
}
public void setOrderId(String orderId) {
	this.orderId = orderId;
}
public Users getUsers() {
	return users;
}
public void setUsers(Users users) {
	this.users = users;
}
public List<OrderItem> getOrderItems() {
	return orderItems;
}
public void setOrderItems(List<OrderItem> orderItems) {
	this.orderItems = orderItems;
}
public LocalDateTime getOrderDate() {
	return orderDate;
}
public void setOrderDate(LocalDateTime orderDate) {
	this.orderDate = orderDate;
}
public LocalDateTime getDeliveryDate() {
	return deliveryDate;
}
public void setDeliveryDate(LocalDateTime deliveryDate) {
	this.deliveryDate = deliveryDate;
}
public Address getShippingAddress() {
	return shippingAddress;
}
public void setShippingAddress(Address shippingAddress) {
	this.shippingAddress = shippingAddress;
}
public PaymentDetails getPaymentDetails() {
	return paymentDetails;
}
public void setPaymentDetails(PaymentDetails paymentDetails) {
	this.paymentDetails = paymentDetails;
}
public double getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(double totalPrice) {
	this.totalPrice = totalPrice;
}
public Integer getTotalDiscountedPrice() {
	return totalDiscountedPrice;
}
public void setTotalDiscountedPrice(Integer totalDiscountedPrice) {
	this.totalDiscountedPrice = totalDiscountedPrice;
}
public Integer getDiscount() {
	return discount;
}
public void setDiscount(Integer discount) {
	this.discount = discount;
}
public String getOrderStatus() {
	return orderStatus;
}
public void setOrderStatus(String orderStatus) {
	this.orderStatus = orderStatus;
}
public int getTotalItem() {
	return totalItem;
}
public void setTotalItem(int totalItem) {
	this.totalItem = totalItem;
}
public LocalDateTime getCreateAt() {
	return createAt;
}
public void setCreateAt(LocalDateTime createAt) {
	this.createAt = createAt;
}
public Orders(Long id, String orderId, Users users, List<OrderItem> orderItems, LocalDateTime orderDate,
		LocalDateTime deliveryDate, Address shippingAddress, PaymentDetails paymentDetails, double totalPrice,
		Integer totalDiscountedPrice, Integer discount, String orderStatus, int totalItem, LocalDateTime createAt) {
	super();
	this.id = id;
	this.orderId = orderId;
	this.users = users;
	this.orderItems = orderItems;
	this.orderDate = orderDate;
	this.deliveryDate = deliveryDate;
	this.shippingAddress = shippingAddress;
	this.paymentDetails = paymentDetails;
	this.totalPrice = totalPrice;
	this.totalDiscountedPrice = totalDiscountedPrice;
	this.discount = discount;
	this.orderStatus = orderStatus;
	this.totalItem = totalItem;
	this.createAt = createAt;
}
public Orders() {

}



}
