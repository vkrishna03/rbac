package com.example.rbac.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbac.db.entity.mapper.UserRole;
import com.example.rbac.db.repository.RoleRepository;
import com.example.rbac.db.repository.UserRepository;
import com.example.rbac.db.repository.UserRoleRepository;
import com.example.rbac.web.request.UserRoleRequest;

@Service
public class UserRoleService {
    
    final Logger logger = LoggerFactory.getLogger(UserRoleService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserRole create(UserRoleRequest userRoleRequest) {
        if(userRoleRequest == null) {
            logger.info("User role request is null");
            return null;
        }
        if(!userRepository.existsByUserId(userRoleRequest.getUserId())) {
            logger.info("User does not exist");
            return null;
        }
        if(!roleRepository.existsByRoleId(userRoleRequest.getRoleId())) {
            logger.info("Role does not exist");
            return null;
        }
        UserRole userRole = UserRole.builder()
                .userId(userRoleRequest.getUserId())
                .roleId(userRoleRequest.getRoleId())
                .build();
        return userRoleRepository.save(userRole);
    }


    
}
