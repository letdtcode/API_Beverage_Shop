package com.example.api_beverage_shop.service.user;

import com.example.api_beverage_shop.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface IUserService {
    public UserDTO findByMail(String email);

    public UserDTO createUser(UserDTO userDTO, String passwordEncode, Set role);

    List<UserDTO> getAllUser();

    UserDTO findUserById(Long id);

    UserDTO updateUser(UserDTO userDTO, Long id);

    UserDTO updateImageProfile(MultipartFile file, Long id);

    UserDTO changePassword(String mail, String passwordNew);
}
