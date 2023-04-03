package com.example.api_beverage_shop.security;

import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.model.Role;
import com.example.api_beverage_shop.model.User;
import com.example.api_beverage_shop.repository.IUserRepository;
import com.example.api_beverage_shop.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) {

        User user = userRepository.findByMail(mail).orElseThrow(() -> new ResourceNotFoundException(AppConstant.MAIL_NOT_FOUND + mail));

        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return new CustomUserDetail(user, grantedAuthorities);
    }
}
