package com.example.ecommerce_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ecommerce_api.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long>{
@Query("select r from Rating r where r.product.id=:productId")
public List<Rating> getAllProductsRating(@Param("productId") Long productId);
}
