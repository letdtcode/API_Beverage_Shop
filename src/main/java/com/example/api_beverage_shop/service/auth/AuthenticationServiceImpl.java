package com.example.api_beverage_shop.service.auth;

import com.example.api_beverage_shop.dto.UserDTO;
import com.example.api_beverage_shop.dto.request.AuthRequest;
import com.example.api_beverage_shop.dto.request.RegisterRequest;
import com.example.api_beverage_shop.dto.request.TokenRefreshRequest;
import com.example.api_beverage_shop.dto.response.AuthResponse;
import com.example.api_beverage_shop.model.Cart;
import com.example.api_beverage_shop.model.Role;
import com.example.api_beverage_shop.model.User;
import com.example.api_beverage_shop.repository.ICartRepository;
import com.example.api_beverage_shop.repository.IRoleRepository;
import com.example.api_beverage_shop.repository.IUserRepository;
import com.example.api_beverage_shop.service.custom.CustomUserDetailService;
import com.example.api_beverage_shop.service.jwt.JwtServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService{
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private JwtServiceImpl jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ICartRepository cartRepository;


    @Override
    public AuthResponse register(@RequestBody RegisterRequest request) {

        User user = new User();
        List<Role> roles = new ArrayList<>();

        AuthResponse authResponse = new AuthResponse();

        for (String role : request.getRoles()) {
            roles.add(roleRepository.findByRoleName(role));
        }

        BeanUtils.copyProperties(request, user, "password");
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Set<Role> set = new HashSet<>();
        roles.stream().forEach(c -> set.add(c));
        Cart cart = Cart.builder().user(user).build();
        cartRepository.save(cart);

        user.setRoles(set);
        user.setCart(cart);

        user = userRepository.save(user);

        UserDetails userDetails = customUserDetailService.loadUserByUsername(user.getMail());

        String access_token = jwtService.generateToken(userDetails);
        String refresh_token = jwtService.generateRefreshToken(userDetails);

        authResponse.setAccessToken(access_token);
        authResponse.setRefreshToken(refresh_token);
        authResponse.setRoles(request.getRoles());
        authResponse.setUserId(user.getUserId());
        return authResponse;
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        AuthResponse authResponse = new AuthResponse();
        UserDTO userDto = new UserDTO();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        UserDetails userDetails = customUserDetailService.loadUserByUsername(request.getEmail());
        User user = userRepository.findByMail(request.getEmail()).get();
        BeanUtils.copyProperties(user, userDto);


        String access_token = jwtService.generateToken(userDetails);
        String refresh_token = jwtService.generateRefreshToken(userDetails);


        authResponse.setAccessToken(access_token);
        authResponse.setRefreshToken(refresh_token);
        authResponse.setRoles(getRoleUser(access_token));
        authResponse.setUserId(user.getUserId());
        return authResponse;
    }

    @Override
    public AuthResponse refresh(TokenRefreshRequest request) {
        AuthResponse authResponse = new AuthResponse();
        String refresh_token = request.getTokenRefresh();
        String access_token = null;

        String username = jwtService.extractUsername(refresh_token);
        UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

        access_token = jwtService.generateToken(userDetails);
        refresh_token = jwtService.generateRefreshToken(userDetails);

        authResponse.setAccessToken(access_token);
        authResponse.setRefreshToken(refresh_token);
        authResponse.setRoles(getRoleUser(access_token));
        return authResponse;
    }

    public Set<String> getRoleUser(String token) {
        Set<String> roles = new HashSet<>();
        String roleUser = jwtService.extractRoles(token);
        for (String role : roleUser.substring(1, roleUser.length() - 1).split(", ")) {
            roles.add(role);
        }
        return roles;
    }
}
