package com.example.ecommerce_api.request;

import java.util.HashSet;
import java.util.Set;

import com.example.ecommerce_api.model.Size;

import lombok.Data;
@Data
public class CreateProductRequest {
private String title;
private String description;
private int price;
private int discountedPrice;
private int discountPercent;
private int quantity;
private String color;
private String brand;
private Set<Size> size=new HashSet<>();
private String imageUrl;
private String topLevelCategory;
private String secondLevelCategory;
private String thirdLevelCategory;

}
