package com.example.ecommerce_api.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	@ManyToOne
	@JoinColumn(name = "users_id",nullable = false)
	private Users users;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "product_id",nullable = false)
	private Product product;
	
	@Column(name = "rating")
	private double rating;
	private LocalDateTime createdAt;
	public Rating(Long id, Users users, Product product, double rating, LocalDateTime createdAt) {
		
		this.id = id;
		this.users = users;
		this.product = product;
		this.rating = rating;
		this.createdAt = createdAt;
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public Rating() {
		// TODO Auto-generated constructor stub
	}
}
