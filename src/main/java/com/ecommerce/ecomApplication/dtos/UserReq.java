package com.ecommerce.ecomApplication.dtos;

import com.ecommerce.ecomApplication.model.UserRole;
import lombok.Data;

@Data
public class UserReq {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressDto addressDto;
}
