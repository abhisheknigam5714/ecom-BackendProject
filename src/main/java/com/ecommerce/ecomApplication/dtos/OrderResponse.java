package com.ecommerce.ecomApplication.dtos;

import com.ecommerce.ecomApplication.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private BigDecimal totalAmount;
    private OrderStatus orderStatus;
    private List<OrderItemDto> items;
    private LocalDateTime createdAt;
}
