package com.example.api_beverage_shop.service.user;

import com.example.api_beverage_shop.dto.UserDTO;
import com.example.api_beverage_shop.dto.request.RegisterRequest;
import com.example.api_beverage_shop.exception.ResourceExistException;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.model.Cart;
import com.example.api_beverage_shop.model.Role;
import com.example.api_beverage_shop.model.RoleName;
import com.example.api_beverage_shop.model.User;
import com.example.api_beverage_shop.repository.IRoleRepository;
import com.example.api_beverage_shop.repository.IUserRepository;
import com.example.api_beverage_shop.util.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDTO findByMail(String email) {
        User user = userRepository.findByMail(email).orElseThrow(() -> new ResourceNotFoundException(AppConstant.MAIL_NOT_FOUND + email));
        return mapper.map(user,UserDTO.class);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO, String passwordEncode, Set role) {
        User user = mapper.map(userDTO,User.class);
        Cart cart = new Cart();
        user.setPassword(passwordEncode);
        user.setRoles(role);
        user.setCart(cart);
        user = userRepository.save(user);
        return mapper.map(user,UserDTO.class);
    }
}
