package com.example.api_beverage_shop.service.role;

import com.example.api_beverage_shop.dto.RoleDTO;

public interface IRoleService {
    public RoleDTO createRole(RoleDTO roleDTO);

    RoleDTO findByRoleName(String roleName);
}
