package com.example.ecommerce_api.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
	@Id
@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String review;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	@JsonIgnore
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "user_id")

	private Users users;
	private LocalDateTime createdAt;
	public Review(Long id, String review, Product product, Users users, LocalDateTime createdAt) {
	
		this.id = id;
		this.review = review;
		this.product = product;
		this.users = users;
		this.createdAt = createdAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public Review() {
		// TODO Auto-generated constructor stub
	}
	
	
}
