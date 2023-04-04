package com.example.api_beverage_shop.service.user;

import com.example.api_beverage_shop.dto.UserDTO;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.model.*;
import com.example.api_beverage_shop.repository.ICartRepository;
import com.example.api_beverage_shop.repository.IUserRepository;
import com.example.api_beverage_shop.util.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ICartRepository cartRepository;

    @Override
    public UserDTO findByMail(String email) {
        User user = userRepository.findByMail(email).orElseThrow(() -> new ResourceNotFoundException(AppConstant.MAIL_NOT_FOUND + email));
        return mapper.map(user,UserDTO.class);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO, String passwordEncode, Set role) {
        User user = mapper.map(userDTO,User.class);
        Cart cart = Cart.builder().user(user).build();
        cartRepository.save(cart);
        user.setPassword(passwordEncode);
        user.setRoles(role);
        user.setCart(cart);
        user = userRepository.save(user);
        return mapper.map(user,UserDTO.class);
    }
    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOList =
                users.stream().map(user -> mapper.map(users, UserDTO.class)).collect(Collectors.toList());
        return userDTOList;
    }
}
