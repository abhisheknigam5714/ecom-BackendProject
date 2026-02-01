package com.ecommerce.ecomApplication.service;

import com.ecommerce.ecomApplication.dtos.AddressDto;
import com.ecommerce.ecomApplication.dtos.UserRes;
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
    public List<UserRes> fetchUser() {
        List<User> all = userRepository.findAll();
        return mapToUserRes();
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
    public boolean updateUser(Long id, User user) {

        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            // Update only required fields
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());

            // If user has address
            if(user.getAddress() != null) {
                existingUser.setAddress(user.getAddress());
            }

            userRepository.save(existingUser);
            return true;
        }

        return false;
    }

    private UserRes mapToUserRes(User user){
        UserRes userRes = new UserRes();
        userRes.setId(String.valueOf(user.getId()));
        userRes.setFirstName(user.getFirstName());
        userRes.setLastName(user.getLastName());
        userRes.setEmail(user.getEmail());
        userRes.setPhone(user.getPhone());
        userRes.setRole(user.getRole());

        if(user.getAddress()!=null){
            AddressDto addressDto= new AddressDto();
            addressDto.setCity(user.getAddress().getCity());
            addressDto.setStreet(user.getAddress().getStreet());
            addressDto.setState(user.getAddress().getState());
            addressDto.setCountry(user.getAddress().getCountry());
            addressDto.setZipcode(user.getAddress().getZipcode());
            userRes.setAddressDto(addressDto);
        }
        return userRes;
    }

}
