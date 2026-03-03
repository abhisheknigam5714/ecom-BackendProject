package com.ecommerce.ecomApplication.dtos;

import lombok.Data;

@Data
public class CartItemRequest {
    private Long productId;

    private Long quantity;

}
