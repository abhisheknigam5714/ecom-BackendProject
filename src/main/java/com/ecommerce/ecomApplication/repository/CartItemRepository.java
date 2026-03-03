package com.ecommerce.ecomApplication.repository;

import com.ecommerce.ecomApplication.model.CartItem;
import com.ecommerce.ecomApplication.model.Product;
import com.ecommerce.ecomApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    CartItem findByUserAndProduct(User user, Product product);

    void deleteByUserAndProduct(User user, Product product);
}
