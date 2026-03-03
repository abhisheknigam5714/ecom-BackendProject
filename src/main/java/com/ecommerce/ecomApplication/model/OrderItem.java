package com.ecommerce.ecomApplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "product_id" ,nullable = false)
    private Product product;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;
}
