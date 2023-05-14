package com.example.api_beverage_shop.service.user;

import com.example.api_beverage_shop.dto.UserDTO;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.model.Cart;
import com.example.api_beverage_shop.model.Role;
import com.example.api_beverage_shop.model.User;
import com.example.api_beverage_shop.model.WishList;
import com.example.api_beverage_shop.repository.ICartRepository;
import com.example.api_beverage_shop.repository.IUserRepository;
import com.example.api_beverage_shop.repository.IWishListRepository;
import com.example.api_beverage_shop.service.storage.ICloudinaryService;
import com.example.api_beverage_shop.util.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IWishListRepository wishListRepository;

    @Autowired
    private ICloudinaryService cloudinaryService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO findByMail(String email) {
        User user = userRepository.findByMail(email).orElseThrow(() -> new ResourceNotFoundException(AppConstant.MAIL_NOT_FOUND + email));
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO, String passwordEncode, Set role) {
        User user = mapper.map(userDTO, User.class);
        Cart cart = Cart.builder().user(user).totalPrice(BigDecimal.valueOf(0)).build();
        WishList wishList = WishList.builder().user(user).build();

        Set<Role> roles = new HashSet<>();
        for (Object obj : role) {
            roles.add(mapper.map(obj, Role.class));
        }
//        Set user name
        Random random = new Random();
        int userId = random.nextInt(2000); // tạo ra số nguyên ngẫu nhiên từ 0 đến 999,999,999
        String userIdString = String.valueOf(userId);
        user.setUserName("user" + userIdString);
//        Set date birth
        DateTimeFormatter dateBirth = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birth = LocalDate.parse(LocalDate.now().format(dateBirth), dateBirth);
        user.setDateOfBirth(birth);
        user.setPassword(passwordEncode);
        user.setGender(1);
        user.setRoles(roles);
        user.setCart(cart);
        user.setWishList(wishList);
        user = userRepository.save(user);
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOList =
                users.stream().map(user -> mapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return userDTOList;
    }

    @Override
    public UserDTO findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(AppConstant.MAIL_NOT_FOUND + id));
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND + id));

        User enityCovert = mapper.map(userDTO, User.class);
        mapper.map(enityCovert, user);
        user = userRepository.save(user);
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateImageProfile(MultipartFile file, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND + id));

        String pathImage = cloudinaryService.store(file);
        user.setAvatar(pathImage);
        user = userRepository.save(user);
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO changePassword(String mail, String passwordNew) {
        User user = userRepository.findByMail(mail).orElseThrow(() ->
                new ResourceNotFoundException(AppConstant.MAIL_NOT_FOUND));
        String passwordEncode = passwordEncoder.encode(passwordNew);
        user.setPassword(passwordEncode);
        userRepository.save(user);
        return mapper.map(user, UserDTO.class);
    }
}
