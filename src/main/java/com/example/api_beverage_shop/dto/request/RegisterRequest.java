package com.example.api_beverage_shop.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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

    @NotBlank
    private Set<String> roles = new HashSet<>(Arrays.asList("USER"));
}
