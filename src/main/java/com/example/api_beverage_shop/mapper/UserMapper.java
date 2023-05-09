package com.example.api_beverage_shop.mapper;

import com.example.api_beverage_shop.dto.UserDTO;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.model.Role;
import com.example.api_beverage_shop.model.User;
import com.example.api_beverage_shop.repository.IRoleRepository;
import com.example.api_beverage_shop.util.AppConstant;
import jakarta.annotation.PostConstruct;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IRoleRepository roleRepository;

    private final Converter<Set<Role>, List<String>> roleToRoleNameConverter = context -> {
        Set<Role> roles = context.getSource();
        List<String> roleName = null;
        if (roles.size() > 0) {
            roleName = new ArrayList<>();
            for (Role role : roles) {
                roleName.add(role.getRoleName());
            }
        }
        return roleName;
    };
    private final Converter<List<String>, Set<Role>> roleNameToRoleConverter = mappingContext -> {
        List<String> roleNameList = mappingContext.getSource();
        Set<Role> roles = null;
        if (roleNameList.size() > 0)
            for (String roleName : roleNameList) {
                roles = new HashSet<>();
                Role role = roleRepository.findByRoleName(roleName)
                        .orElseThrow(() -> new ResourceNotFoundException(AppConstant.ROLE_NOT_FOUND_WITH_NAME + roleName));
                roles.add(role);
            }
        return roles;
    };

    @PostConstruct
    public void init() {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.createTypeMap(User.class, UserDTO.class).addMappings(mapper -> mapper.using(roleToRoleNameConverter).map(User::getRoles, UserDTO::setRoleName));
        mapper.createTypeMap(UserDTO.class, User.class).setPropertyCondition(Conditions.isNotNull()).addMappings(mapper -> mapper.using(roleNameToRoleConverter).map(UserDTO::getRoleName, User::setRoles));
    }

    public UserDTO toDTO(User user) {
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        return userDTO;
    }

    public User toEntity(UserDTO dto) {
        return mapper.map(dto, User.class);
    }
}
