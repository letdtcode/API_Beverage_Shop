package com.example.api_beverage_shop.service.user;

import com.example.api_beverage_shop.dto.UserDTO;
import com.example.api_beverage_shop.dto.request.RegisterRequest;
import com.example.api_beverage_shop.exception.ResourceExistException;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
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
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDTO createUser(RegisterRequest request) {
        String mail = request.getMail();
        if(userRepository.existsByMail(mail)) {
            throw new ResourceExistException(AppConstant.USER_EXIST);
        }

        Role role = roleRepository.findByRoleName(RoleName.USER);
        if(Objects.isNull(role)) {
            throw new ResourceNotFoundException(AppConstant.ROLE_NOT_FOUND + RoleName.USER);
        }

        User user = mapper.map(request, User.class);
        user.setRoles(Set.copyOf(Arrays.asList(role)));
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);

        return mapper.map(user, UserDTO.class);
    }


}
