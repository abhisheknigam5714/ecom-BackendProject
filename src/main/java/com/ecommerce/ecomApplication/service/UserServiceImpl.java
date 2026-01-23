package com.ecommerce.ecomApplication.service;

import com.ecommerce.ecomApplication.model.User;
import com.ecommerce.ecomApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;


    @Override
    public List<User> fetchUser() {
        return userRepository.findAll();
    }

    @Override
    public String addUser(User user) {
        User savedUser = userRepository.save(user);
        if (savedUser!=null) {
            return "User Added Successfully";
        } else return "user not added";

    }

    @Override
    public Optional<User> findUser(Long id) {
       return userRepository.findById(id);
    }

    @Override
    public boolean updateUser(Long id,User user) {
        return userRepository.findById(id).map(userIt-> {

                    userIt.setFirstName(user.getFirstName());
                    userIt.setLastName(user.getLastName());
                    userRepository.save(userIt);
                    return true;
                }
            ).orElse(false);







    }
}
