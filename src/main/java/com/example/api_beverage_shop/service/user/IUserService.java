package com.example.api_beverage_shop.service.user;

import com.example.api_beverage_shop.dto.UserDTO;
import com.example.api_beverage_shop.dto.request.RegisterRequest;

public interface IUserService {
    UserDTO createUser(RegisterRequest userDTO);
}
