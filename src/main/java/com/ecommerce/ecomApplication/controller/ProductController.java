package com.ecommerce.ecomApplication.controller;

import com.ecommerce.ecomApplication.dtos.ProductRequest;
import com.ecommerce.ecomApplication.dtos.ProductResponse;
import com.ecommerce.ecomApplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/api/")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productReq){
        ProductResponse Res = productService.createProduct(productReq);
        return new ResponseEntity<>(Res, HttpStatus.CREATED);
    }
}
