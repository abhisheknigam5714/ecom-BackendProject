package com.ecommerce.ecomApplication.dtos;

import com.ecommerce.ecomApplication.model.Order;
import com.ecommerce.ecomApplication.model.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {

    private Long id;
    private Long productId;
    private BigDecimal price;
    private Integer quantity;
}
