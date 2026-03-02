package com.ecommerce.ecomApplication.service;

import com.ecommerce.ecomApplication.dtos.AddressDto;
import com.ecommerce.ecomApplication.dtos.UserReq;
import com.ecommerce.ecomApplication.dtos.UserRes;
import com.ecommerce.ecomApplication.model.Address;
import com.ecommerce.ecomApplication.model.User;
import com.ecommerce.ecomApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;


    @Override
    public List<UserRes> fetchUser(){
        List<User> all = userRepository.findAll();
        return all.stream().map(user-> mapToUserRes(user)).collect(Collectors.toList());
    }

    @Override
    public String addUser(UserReq req) {
        User user= new User();
        mapReqtoUser(user, req);

        User savedUser = userRepository.save(user);
        if (savedUser!=null) {
            return "User Added Successfully";
        } else return "user not added";

    }

    @Override
    public Optional<UserRes> findUser(Long id) {
       return userRepository.findById(id).map(this:: mapToUserRes);
    }



    @Override
    public boolean updateUser(Long id, UserReq userReq) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        mapReqtoUser(existingUser, userReq);
        userRepository.save(existingUser);
        return true;
    }

    private void mapReqtoUser(User user, UserReq req){

        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
    if(req.getAddressDto()!=null){
        Address address= new Address();
        address.setId(req.getAddressDto().getId());
        address.setCity(req.getAddressDto().getCity());
        address.setStreet(req.getAddressDto().getStreet());
        address.setState(req.getAddressDto().getState());
        address.setCountry(req.getAddressDto().getCountry());
        address.setZipcode(req.getAddressDto().getZipcode());
        user.setAddress(address);
    }

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
            addressDto.setId(user.getAddress().getId());
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
