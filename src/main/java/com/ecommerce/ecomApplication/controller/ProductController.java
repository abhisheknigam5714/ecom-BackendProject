package com.ecommerce.ecomApplication.controller;

import com.ecommerce.ecomApplication.dtos.ProductRequest;
import com.ecommerce.ecomApplication.dtos.ProductResponse;
import com.ecommerce.ecomApplication.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {


    private  final ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productReq) {
        ProductResponse Res = productService.createProduct(productReq);
        return new ResponseEntity<>(Res, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest productReq) {

        return productService.updateProduct(id, productReq)
                .map(ResponseEntity::ok)                       // ProductResponse -> ResponseEntity<ProductResponse>
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> fecthAllProduct(){

        return new ResponseEntity<>(productService.fetchAllProduct(),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        boolean active = productService.deleteProduct(id);
        return active ? ResponseEntity.noContent().build():ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProduct(@RequestParam String keyword){
        return ResponseEntity.ok(productService.searchProduct(keyword));

    }
}
