package com.example.api_beverage_shop.dto;

import com.example.api_beverage_shop.model.Cart;
import com.example.api_beverage_shop.model.Order;
import com.example.api_beverage_shop.model.Role;

import java.time.LocalDate;
import java.util.List;

public class UserDTO {
    private String userName;
    private String firstName;
    private String lastName;
    private int gender;
    private LocalDate dateOfBirth;
    private String mail;
    private String address;
    private String phone;
    private RoleDTO role;
    private CartDTO cart;
    private List<OrderDTO> orders;
}
