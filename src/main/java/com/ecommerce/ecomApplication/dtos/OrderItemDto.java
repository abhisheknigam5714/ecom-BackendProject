package com.ecommerce.ecomApplication.dtos;

import com.ecommerce.ecomApplication.model.Order;
import com.ecommerce.ecomApplication.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long id;
    private Long productId;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal subTotal;

    public OrderItemDto(Long id, Long id1, Integer quantity, BigDecimal price, BigDecimal multiply) {

    }
    }


