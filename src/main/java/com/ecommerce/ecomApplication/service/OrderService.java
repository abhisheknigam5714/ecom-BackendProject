package com.ecommerce.ecomApplication.service;

import com.ecommerce.ecomApplication.dtos.OrderResponse;
import com.ecommerce.ecomApplication.model.CartItem;
import com.ecommerce.ecomApplication.model.User;
import com.ecommerce.ecomApplication.repository.CartItemRepository;
import com.ecommerce.ecomApplication.repository.OrderRepository;
import com.ecommerce.ecomApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final  CartService cartService;
    public OrderResponse createOrder(String userId) {
        //validate for cart
        //validate for user
        //calculate total price
        //create order
        //clear the cart
       List< CartItem> cart= cartService.getAllItems(userId);
       if(cart.isEmpty()){

       }
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
        if(userOpt.isEmpty()){

        }


    }
}
