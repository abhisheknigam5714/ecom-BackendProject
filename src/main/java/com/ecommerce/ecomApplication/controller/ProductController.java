package com.ecommerce.ecomApplication.controller;

import com.ecommerce.ecomApplication.dtos.ProductRequest;
import com.ecommerce.ecomApplication.dtos.ProductResponse;
import com.ecommerce.ecomApplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productReq) {
        ProductResponse Res = productService.createProduct(productReq);
        return new ResponseEntity<>(Res, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest productReq) {

        return productService.updateProduct(id, productReq)
                .map(ResponseEntity::ok)                       // ProductResponse -> ResponseEntity<ProductResponse>
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/api/product")
    public ResponseEntity<ProductResponse> fecthAllProduct(){
        productService.fetchAllProduct();
    }
}
