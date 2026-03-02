package com.ecommerce.ecomApplication.service;

import com.ecommerce.ecomApplication.dtos.UserReq;
import com.ecommerce.ecomApplication.dtos.UserRes;
import com.ecommerce.ecomApplication.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<UserRes> fetchUser();


    public String addUser(UserReq user);

    public Optional<UserRes> findUser(Long id);

    public boolean updateUser(Long id,UserReq user);
}
