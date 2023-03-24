package com.example.api_beverage_shop.controller;

import com.example.api_beverage_shop.dto.RoleDTO;
import com.example.api_beverage_shop.repository.IRoleRepository;
import com.example.api_beverage_shop.service.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @PostMapping("/role")
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        return new ResponseEntity<RoleDTO>(roleService.createRole(roleDTO), HttpStatus.CREATED);
    }
}
