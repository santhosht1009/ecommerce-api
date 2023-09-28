package com.example.ecommerce_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CartItem {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
		private Long id;
	@JsonIgnore
	@ManyToOne
	private Cart cart;
	@ManyToOne
	private Product product;
	private String size;
	private int quantity;
	private Integer price;
	private Integer discountedPrice;
	private Long usersId;
	 
	
}
