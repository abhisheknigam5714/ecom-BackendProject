package com.ecommerce.ecomApplication.controller;

import com.ecommerce.ecomApplication.dtos.CartItemRequest;
import com.ecommerce.ecomApplication.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartItemController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestHeader("X-User-ID") String userId, @RequestBody CartItemRequest req) {
        boolean b = cartService.addToCart(userId, req);
        if (!b) {
            return ResponseEntity.badRequest().body("Product is out of Stock or user not found or Product not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<?> removeFromCart(
            @RequestHeader("X-User-ID") String userId,
          @PathVariable  Long productId
    ){
       boolean res= cartService.removetItemFromCart(userId,productId);
       return res? ResponseEntity.noContent().build():ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllItems(@RequestHeader("\"X-User-ID") String userId){

        return ResponseEntity.ok(cartService.getAllItems(userId));
    }
}
