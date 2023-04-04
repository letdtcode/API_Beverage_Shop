package com.example.api_beverage_shop.util;

import com.example.api_beverage_shop.dto.RoleDTO;
import com.example.api_beverage_shop.dto.UserDTO;
import com.example.api_beverage_shop.dto.response.AuthResponse;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.model.Cart;
import com.example.api_beverage_shop.model.Role;
import com.example.api_beverage_shop.model.User;
import com.example.api_beverage_shop.repository.IRoleRepository;
import com.example.api_beverage_shop.repository.IUserRepository;
import com.example.api_beverage_shop.service.role.IRoleService;
import com.example.api_beverage_shop.service.user.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@AllArgsConstructor
public class AdminUserInitializer implements CommandLineRunner {
//    @Autowired
//    @Qualifier("CustomUserDetailsService")
//    private final UserDetailsService userDetailsService;

    @Autowired
    private final IUserService userService;

    @Autowired
    private final IRoleRepository roleRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final IUserRepository userRepository;

    @Override
    public void run(String... args) {

        //        Create role Admin
        Optional<Role> roleAdmin = roleRepository.findByRoleName("Admin");
        if (!roleAdmin.isPresent()) {
            Role admin = Role.builder().roleName("Admin").build();
            roleRepository.save(admin);
        }

        //        Create role Client
        Optional<Role> roleClient = roleRepository.findByRoleName("Client");
        if (!roleClient.isPresent()) {
            Role client = Role.builder().roleName("Client").build();
            roleRepository.save(client);
        }

        //        Create 1 account admin
        Optional<User> adminUser = userRepository.findByMail("admin@gmail.com");
        if (!adminUser.isPresent()) {
            DateTimeFormatter dateBirth = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birth = LocalDate.parse("23/12/2002", dateBirth);

            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByRoleName("Admin").get());

            UserDTO admin = new UserDTO(null, "admin", "Duc Thanh", "Nguyen", 0, birth, "admin@gmail.com", "Cau Xay, Tan Phu, TP.HCM", "0342293128", null, null);
            userService.createUser(admin, passwordEncoder.encode("admin"), roles);
        }
    }
}
