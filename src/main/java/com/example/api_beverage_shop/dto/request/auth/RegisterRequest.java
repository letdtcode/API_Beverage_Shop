package com.example.api_beverage_shop.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Data
public class RegisterRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String mail;

    @NotBlank
    private String password;

//    @NotBlank
//    private Integer gender;

//    @NotBlank
//    private LocalDate dateOfBirth;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;

    @NotBlank
    private Set<String> roles = new HashSet<>(Arrays.asList("Client"));
}
