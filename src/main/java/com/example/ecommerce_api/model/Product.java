package com.example.ecommerce_api.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



import jakarta.persistence.CascadeType;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	private String title;

	private String color;

	private String brand;
	private String description;
	private int price;
	

	private int discountedPrice;

	private int discountPercent;

	private int quantity;
	
	@Embedded
	@ElementCollection
	private Set<Size> sizes=new HashSet<>();

	private String imageUrl;
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Rating> ratings=new ArrayList<>();
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Review> reviews=new ArrayList<>();
	private int numRatings;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	private LocalDateTime createdAt;
	
	
}
