package com.example.api_beverage_shop.dto;

import com.example.api_beverage_shop.model.Cart;
import com.example.api_beverage_shop.model.Order;
import com.example.api_beverage_shop.model.Role;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @Length(min = 5)
    private String userName;

    @NotEmpty
    private String firstName;
    private String lastName;
    private int gender;
    private LocalDate dateOfBirth;
    private String mail;
    private String address;
    private String phone;
    private String avatar;
    private MultipartFile imgAvatar; //lưu hình
    private RoleDTO role;
    private CartDTO cart;
    private List<OrderDTO> orders;
}
