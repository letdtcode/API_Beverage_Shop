package com.example.api_beverage_shop.service.role;

import com.example.api_beverage_shop.dto.RoleDTO;
import com.example.api_beverage_shop.model.Role;
import com.example.api_beverage_shop.repository.IRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService{
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        roleRepository.save(modelMapper.map(roleDTO, Role.class));
        return roleDTO;
    }
}
