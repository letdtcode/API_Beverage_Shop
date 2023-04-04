package com.example.api_beverage_shop.service.user;

import com.example.api_beverage_shop.dto.UserDTO;
import com.example.api_beverage_shop.dto.request.RegisterRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IUserService {
    public UserDTO findByMail(String email);

    public UserDTO createUser(UserDTO userDTO, String passwordEncode, Set role);

    List<UserDTO> getAllUser();
}
