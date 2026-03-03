package com.ecommerce.ecomApplication.service;

import com.ecommerce.ecomApplication.dtos.OrderItemDto;
import com.ecommerce.ecomApplication.dtos.OrderResponse;
import com.ecommerce.ecomApplication.model.*;
import com.ecommerce.ecomApplication.repository.CartItemRepository;
import com.ecommerce.ecomApplication.repository.OrderRepository;
import com.ecommerce.ecomApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartService cartService;


    public Optional<OrderResponse> createOrder(String userId) {
        //validate for cart
        //validate for user
        //calculate total price
        //create order
        //clear the cart
        List<CartItem> cart = cartService.getAllItems(userId);
        if (cart.isEmpty()) {
            return Optional.empty();
        }
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
        if (userOpt.isEmpty()) {
            return Optional.empty();
        }
        User user = userOpt.get();
        BigDecimal price = cart.stream().
                map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        Order order = new Order();
        order.setUser(user);
        order.setOrderStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(price);
        List<OrderItem> orderItem = cart.stream()
                .map(item -> new OrderItem(
                        null,
                        item.getPrice(),
                        item.getProduct(),
                        Math.toIntExact(item.getQuantity()),
                        order
                )).toList();
        order.setItems(orderItem);
        Order saveorder = orderRepository.save(order);
        cartService.clearCart(userId);
        return Optional.of(mapToOrderResponse(saveorder));
    }

    private OrderResponse mapToOrderResponse(Order saveorder) {
        return new OrderResponse(saveorder.getId(),saveorder.getTotalAmount(),  saveorder.getOrderStatus(), saveorder.getItems().stream().map(item ->
                new OrderItemDto(
                        item.getId(),
                        item.getProduct().getId(),
                        item.getQuantity(),
                        item.getPrice(),
                        item.getPrice().multiply(new BigDecimal(item.getQuantity()))

                )).toList()
                , saveorder.getCreatedAt());

    }
}
