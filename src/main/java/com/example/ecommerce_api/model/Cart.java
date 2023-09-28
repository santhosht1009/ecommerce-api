package com.example.ecommerce_api.model;

import java.util.HashSet;
import java.util.Set;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
		private Long id;
	@OneToOne
	@JoinColumn(name = "users_id",nullable = false)
	private Users users;
	@OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
	@Column(name = "cart_items")
	private Set<CartItem> cartItems=new HashSet<>();
	@Column(name = "total_price")
	private double totalPrice;
	@Column(name = "total_item")
	private int totalItem;
	private int totalDiscountedPrice;
	private int discount;
	public Cart(Long id, Users users, Set<CartItem> cartItems, double totalPrice, int totalItem,
			int totalDiscountedPrice, int discount) {
		super();
		this.id = id;
		this.users = users;
		this.cartItems = cartItems;
		this.totalPrice = totalPrice;
		this.totalItem = totalItem;
		this.totalDiscountedPrice = totalDiscountedPrice;
		this.discount = discount;
	}
	public Cart() {
	// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Set<CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}
	public int getTotalDiscountedPrice() {
		return totalDiscountedPrice;
	}
	public void setTotalDiscountedPrice(int totalDiscountedPrice) {
		this.totalDiscountedPrice = totalDiscountedPrice;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	
	
	
}
