package com.ecommerce.ecomApplication.controller;

import com.ecommerce.ecomApplication.dtos.OrderResponse;
import com.ecommerce.ecomApplication.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse>  createOrder(@RequestHeader("X-User-Id") String userId){
        OrderResponse order= orderService.createOrder(userId);
        return new  ResponseEntity<>(order, HttpStatus.CREATED);

    }


}
