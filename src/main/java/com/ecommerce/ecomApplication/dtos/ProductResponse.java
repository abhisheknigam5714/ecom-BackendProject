package com.ecommerce.ecomApplication.dtos;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean active;
    private Integer stockQuantity;
    private String category;
    private String imageUrl;
}
