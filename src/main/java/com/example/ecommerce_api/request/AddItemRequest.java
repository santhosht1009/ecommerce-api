package com.example.ecommerce_api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class AddItemRequest {
private Long productId;
private String size;
private int quantity;
private Integer price;

}
