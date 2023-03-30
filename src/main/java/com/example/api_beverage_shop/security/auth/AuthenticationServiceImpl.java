package com.example.api_beverage_shop.security.auth;

import com.example.api_beverage_shop.dto.UserDTO;
import com.example.api_beverage_shop.dto.request.LoginRequest;
import com.example.api_beverage_shop.dto.request.RegisterRequest;
import com.example.api_beverage_shop.dto.request.TokenRefreshRequest;
import com.example.api_beverage_shop.dto.response.AuthResponse;
import com.example.api_beverage_shop.repository.ICartRepository;
import com.example.api_beverage_shop.repository.IRoleRepository;
import com.example.api_beverage_shop.repository.IUserRepository;
import com.example.api_beverage_shop.security.jwt.JwtServiceImpl;
import com.example.api_beverage_shop.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private JwtServiceImpl jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ICartRepository cartRepository;


    @Override
    public UserDTO register(@RequestBody RegisterRequest request) {
        return userService.createUser(request);
    }

    @Override
    public AuthResponse authenticate(LoginRequest request) {
        AuthResponse authResponse = new AuthResponse();
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication auth = authenticationManager.authenticate(authReq);
        String token = jwtService.generateToken(auth);
        String refreshToken = jwtService.generateRefreshToken(auth);
        authResponse.setAccessToken(token);
        authResponse.setRefreshToken(refreshToken);
        authResponse.setRoles(getRoleUser(token));
        return authResponse;
    }

//    @Override
//    public AuthResponse refresh(TokenRefreshRequest request) {
//        AuthResponse authResponse = new AuthResponse();
//        String refresh_token = request.getTokenRefresh();
//        String access_token = null;
//
//        String username = jwtService.extractUsername(refresh_token);
//        UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
//
//        access_token = jwtService.generateToken(userDetails);
//        refresh_token = jwtService.generateRefreshToken(userDetails);
//
//        authResponse.setAccessToken(access_token);
//        authResponse.setRefreshToken(refresh_token);
//        authResponse.setRoles(getRoleUser(access_token));
//        return authResponse;
//    }

    public Set<String> getRoleUser(String token) {
        Set<String> roles = new HashSet<>();
        String roleUser = jwtService.extractRoles(token);
        for (String role : roleUser.substring(1, roleUser.length() - 1).split(", ")) {
            roles.add(role);
        }
        return roles;
    }
}
