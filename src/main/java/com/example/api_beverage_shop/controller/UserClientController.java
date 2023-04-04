package com.example.api_beverage_shop.controller;

import com.example.api_beverage_shop.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class UserClientController {
    @Autowired
    private IUserService userService;

    @GetMapping("/user")
    public ResponseEntity<?> getInfoUserByMail(@RequestParam("mail") String mail) {
        return ResponseEntity.ok(userService.findByMail(mail));
    }
}
