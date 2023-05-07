package com.example.api_beverage_shop.service.role;

import com.example.api_beverage_shop.dto.RoleDTO;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.model.Role;
import com.example.api_beverage_shop.repository.IRoleRepository;
import com.example.api_beverage_shop.util.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public RoleDTO createRole(String roleName) {
        Role role = Role.builder().roleName(roleName).build();
        role = roleRepository.save(role);
        return mapper.map(role, RoleDTO.class);
    }

    @Override
    public RoleDTO findByRoleName(String roleName) {
        Role role = roleRepository.findByRoleName(roleName).orElseThrow(() -> new ResourceNotFoundException(AppConstant.ROLE_NOT_FOUND_WITH_NAME + roleName));
        return mapper.map(role, RoleDTO.class);
    }
}
