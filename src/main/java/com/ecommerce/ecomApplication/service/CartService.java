package com.ecommerce.ecomApplication.service;

import com.ecommerce.ecomApplication.dtos.CartItemRequest;
import com.ecommerce.ecomApplication.model.CartItem;
import com.ecommerce.ecomApplication.model.Product;
import com.ecommerce.ecomApplication.model.User;
import com.ecommerce.ecomApplication.repository.CartItemRepository;
import com.ecommerce.ecomApplication.repository.ProductRepository;
import com.ecommerce.ecomApplication.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository; 

    public  boolean addToCart(String userId, CartItemRequest req) {
        Optional<Product> prodcutOpt = productRepository.findById(req.getProductId());
        if(prodcutOpt.isEmpty()){
            return false;
        }
        Product product = prodcutOpt.get();
        if(product.getStockQuantity()< req.getQuantity()) return false;

        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
        if(userOpt.isEmpty()) return false;
        User user = userOpt.get();

        CartItem existingCart = cartItemRepository.findByUserAndProduct(user, product);
        if(existingCart !=null){
            //update the quantity
            existingCart.setQuantity((long) (existingCart.getQuantity()+req.getQuantity()));
            existingCart.setPrice(existingCart.getPrice().multiply(BigDecimal.valueOf(req.getQuantity())));
            cartItemRepository.save(existingCart);

        }
        else{
            //create new cart item
            CartItem cartItem=  new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(req.getQuantity());
           cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(req.getQuantity())));
           cartItemRepository.save(cartItem);
        }
    return true;
    }

    public boolean removetItemFromCart(String userId, Long productId) {
        Optional<Product> productOpt = productRepository.findById(productId);
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
        if(productOpt.isPresent() && userOpt.isPresent()){
            cartItemRepository.deleteByUserAndProduct(userOpt.get(),productOpt.get());
            return true;
        }
        return false;
    }

    public List<CartItem> getAllItems(String id){
      return userRepository.findById(Long.valueOf(id)).map(cartItemRepository::findByUser).orElseGet(List ::of);

    }

    public void clearCart(String userId) {
        userRepository.findById(Long.valueOf(userId)).ifPresent(user->cartItemRepository.deleteByUser(user));
    }

}
