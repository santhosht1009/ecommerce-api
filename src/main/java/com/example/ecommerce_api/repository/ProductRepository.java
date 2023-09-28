package com.example.ecommerce_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ecommerce_api.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	
//	@Query("select p from Product p"+
//	"where (p.category.name=:category  or :category='')"+
//			"AND ((:minPrice is NULL AND :maxPrice is NULL) OR (p.discountedPrice BETWEEN :minPrice AND :maxPrice ))"+
//			"AND (:minDiscount is NULL Or p.discountPercent>=:minDiscount)"+
//	"ORDER BY"+
//			"CASE WHEN :sort='price_low' THEN p.discountedPrice END ASC,"+
//			"CASE WHEN :sort='price_high' THEN p.discountedPrice END DESC"
//			
//			)
//	public List<Product> filterProducts(@Param("category") String category,
//			@Param("minPrice") Integer minPrice,
//			@Param("maxPrice") Integer maxPrice,
//			@Param("minDiscount") Integer minDiscount,
//			@Param("sort") String sort);
	
	
	@Query("select p from Product p " +
		       "where (p.category.name = :category or :category = '') " +
		       "and ((:minPrice is null and :maxPrice is null) or (p.discountedPrice between :minPrice and :maxPrice)) " +
		       "and (:minDiscount is null or p.discountPercent >= :minDiscount) " +
		       "order by " +
		       "case when :sort = 'price_low' then p.discountedPrice end asc, " +
		       "case when :sort = 'price_high' then p.discountedPrice end desc"
		)
		public List<Product> filterProducts(@Param("category") String category,
		                                    @Param("minPrice") Integer minPrice,
		                                    @Param("maxPrice") Integer maxPrice,
		                                    @Param("minDiscount") Integer minDiscount,
		                                    @Param("sort") String sort);
@Query("select pc from Product pc where pc.category.name = :category ")
	public List<Product> findByCategory(@Param("category") String category);
	
}
