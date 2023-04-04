package com.example.api_beverage_shop.controller.admin;

import com.example.api_beverage_shop.dto.RoleDTO;
import com.example.api_beverage_shop.repository.IRoleRepository;
import com.example.api_beverage_shop.service.role.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @PostMapping("/role")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<RoleDTO> createRole(@RequestParam("name") String roleName) {
        return ResponseEntity.ok(roleService.createRole(roleName));
    }
}
