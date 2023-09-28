package com.example.ecommerce_api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ecommerce_api.exception.ProductException;
import com.example.ecommerce_api.model.Category;
import com.example.ecommerce_api.model.Product;
import com.example.ecommerce_api.repository.CategoryRepository;
import com.example.ecommerce_api.repository.ProductRepository;
import com.example.ecommerce_api.request.CreateProductRequest;
@Service
public class ProductServiceImplementation implements ProductService {
private ProductRepository productRepository;
private CategoryRepository categoryRepository;
private UserService userService;


	
	
	
	public ProductServiceImplementation(ProductRepository productRepository, CategoryRepository categoryRepository,
		UserService userService) {

	this.productRepository = productRepository;
	this.categoryRepository = categoryRepository;
	this.userService = userService;
}

	@Override
	public Product createProduct(CreateProductRequest req) {
	Category topLevel=categoryRepository.findByName(req.getTopLevelCategory());
		if(topLevel==null) {
			Category topLevelCategory=new Category();
			topLevelCategory.setName(req.getTopLevelCategory());
			topLevelCategory.setLevel(1);
			topLevel=categoryRepository.save(topLevelCategory);
		}
	
		Category secondLevel=categoryRepository.findByNameAndParent(req.getSecondLevelCategory(),topLevel.getName());
				if(secondLevel==null) {
					Category secondLevelCategory=new Category();
					secondLevelCategory.setName(req.getSecondLevelCategory());
					secondLevelCategory.setLevel(2);
					secondLevelCategory.setParentCategory(topLevel);
					secondLevel=categoryRepository.save(secondLevelCategory);
				}
				
				Category thirdLevel=categoryRepository.findByNameAndParent(req.getThirdLevelCategory(),secondLevel.getName());
				if(thirdLevel==null) {
					Category thirdLevelCategory=new Category();
					thirdLevelCategory.setName(req.getThirdLevelCategory());
					thirdLevelCategory.setLevel(3);
					thirdLevelCategory.setParentCategory(secondLevel);
					thirdLevel=categoryRepository.save(thirdLevelCategory);
				}
				
				Product product=new Product();
				product.setTitle(req.getTitle());
				product.setColor(req.getColor());
				product.setDescription(req.getDescription());
				product.setDiscountedPrice(req.getDiscountedPrice());
				product.setDiscountPercent(req.getDiscountPercent());
				product.setImageUrl(req.getImageUrl());
				product.setBrand(req.getBrand());
				product.setPrice(req.getPrice());
				product.setSizes(req.getSize());
				product.setQuantity(req.getQuantity());
				product.setCategory(thirdLevel);
				product.setCreatedAt(LocalDateTime.now());
				Product savedProduct=productRepository.save(product);
				
		return savedProduct;
	}

	@Override
	public String deleteProduct(Long productid) throws ProductException {
		Product product=findProductById(productid);
		product.getSizes().clear();
		productRepository.delete(product);
		return "Product deleted Successfully";
	}

	@Override
	public Product updateProduct(Long productid, Product req) throws ProductException {
		Product product=findProductById(productid);
		if(req.getQuantity()!=0) {
			product.setQuantity(req.getQuantity());
		}
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(Long productid) throws ProductException {
		Optional<Product> opt=productRepository.findById(productid);
		if(opt.isPresent())
		{return opt.get();
	}
		
		throw new ProductException("Product not found with id:"+productid);
	}

	@Override
	public List<Product> findProductByCategory(String category) {
		// TODO Auto-generated method stub
		return productRepository.findByCategory(category);
	}

	@Override
	public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
	Pageable pageable=PageRequest.of(pageNumber,pageSize);
	List<Product> products=productRepository.filterProducts(category, minPrice, maxPrice, minDiscount, sort);
	if(!colors.isEmpty()) {
		products=products.stream().filter(p->colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
	}
	
	
	if(stock!=null) {
		if(stock.equals("in_stock")) {
			products=products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
		}
		else if(stock.equals("out_of_stock")) {
			products=products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
		}
	}
	
	int startIndex=(int)pageable.getOffset();
	int endIndex=Math.min(startIndex + pageable.getPageSize(), products.size());
	List<Product> pageContent=products.subList(startIndex, endIndex);
	Page<Product> filteredProduct =new PageImpl<>(pageContent,pageable,products.size());
		return filteredProduct;
	}

	@Override
	public List<Product> findAllProducts() {
		
		return productRepository.findAll();
	}

}
