package com.example.api_beverage_shop.service.role;

import com.example.api_beverage_shop.dto.RoleDTO;

public interface IRoleService {
    public RoleDTO createRole(String roleName);

    public RoleDTO findByRoleName(String roleName);
}
