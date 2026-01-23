package com.ecommerce.ecomApplication.service;

import com.ecommerce.ecomApplication.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> fetchUser();


    public String addUser(User user);

    public Optional<User> findUser(Long id);

    public boolean updateUser(Long id,User user);
}
