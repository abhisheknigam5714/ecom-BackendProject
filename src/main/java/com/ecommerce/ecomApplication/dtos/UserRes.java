package com.ecommerce.ecomApplication.dtos;

import com.ecommerce.ecomApplication.model.UserRole;
import lombok.Data;

@Data
public class UserRes {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole role;
    private AddressDto addressDto;
}
