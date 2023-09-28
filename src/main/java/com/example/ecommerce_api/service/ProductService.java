package com.example.ecommerce_api.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.ecommerce_api.exception.ProductException;
import com.example.ecommerce_api.model.Product;
import com.example.ecommerce_api.request.CreateProductRequest;

public interface ProductService {

	public Product createProduct(CreateProductRequest req);
	public String deleteProduct(Long productid) throws ProductException;
	public Product updateProduct(Long productid,Product req) throws ProductException;
	public Product findProductById(Long productid) throws ProductException;
	public List<Product> findProductByCategory(String category);
	
	
	
	
	
	
	public Page<Product> getAllProduct(String category,List<String> colors,List<String> sizes,Integer minPrice,Integer maxPrice,
			Integer minDiscount,String sort,String stock,Integer pageNumber,Integer pageSize);
	public List<Product> findAllProducts();
}
