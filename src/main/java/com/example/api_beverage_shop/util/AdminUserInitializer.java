package com.example.api_beverage_shop.util;

import com.example.api_beverage_shop.dto.UserDTO;
import com.example.api_beverage_shop.model.Cart;
import com.example.api_beverage_shop.model.Role;
import com.example.api_beverage_shop.model.User;
import com.example.api_beverage_shop.model.WishList;
import com.example.api_beverage_shop.repository.ICartRepository;
import com.example.api_beverage_shop.repository.IRoleRepository;
import com.example.api_beverage_shop.repository.IUserRepository;
import com.example.api_beverage_shop.repository.IWishListRepository;
import com.example.api_beverage_shop.service.user.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@AllArgsConstructor
public class AdminUserInitializer implements CommandLineRunner {

    @Autowired
    private final IRoleRepository roleRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final IUserRepository userRepository;

    @Autowired
    private final ICartRepository cartRepository;

    @Autowired
    private final IWishListRepository wishListRepository;

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

            User admin = User.builder()
                    .Id(1L).userName("admin")
                    .password(passwordEncoder.encode("admin"))
                    .firstName("Duc Thanh")
                    .lastName("Nguyen")
                    .gender(1)
                    .dateOfBirth(birth)
                    .mail("admin@gmail.com")
                    .address("Cau Xay, Q9, TP.HCM")
                    .phone("0342293128")
                    .roles(roles)
                    .build();

            Cart cart = Cart.builder().Id(1L).totalPrice(BigDecimal.valueOf(0)).user(admin).build();
            WishList wishList = WishList.builder().Id(1L).user(admin).build();

            admin.setWishList(wishList);
            admin.setCart(cart);

            wishListRepository.save(wishList);
            cartRepository.save(cart);
            userRepository.save(admin);
        }
    }
}
