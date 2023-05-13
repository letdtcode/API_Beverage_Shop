package com.example.api_beverage_shop.controller.client;

import com.example.api_beverage_shop.dto.UserDTO;
import com.example.api_beverage_shop.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class UserClientController {
    @Autowired
    private IUserService userService;

    @GetMapping("/usermail")
    public ResponseEntity<?> getInfoUserByMail(@RequestParam("mail") String mail) {
        return ResponseEntity.ok(userService.findByMail(mail));
    }

    @GetMapping("/userId")
    public ResponseEntity<?> getInfoUserById(@RequestParam("id") Long Id) {
        return ResponseEntity.ok(userService.findUserById(Id));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateInfoUser(@PathVariable("id") Long Id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(userDTO, Id));
    }

    @PutMapping("/user/img/{id}")
    public ResponseEntity<?> updateImageUser(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.updateImageProfile(file, id));
    }

    @PutMapping("/user/changepass")
    public ResponseEntity<?> changePassword(@Param("mail") String mail, @Param("newPass") String newPass) {
        return ResponseEntity.ok(userService.changePassword(mail, newPass));
    }


}
