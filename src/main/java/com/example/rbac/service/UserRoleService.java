package com.example.rbac.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbac.db.entity.mapper.UserRole;
import com.example.rbac.db.repository.RoleRepository;
import com.example.rbac.db.repository.UserRepository;
import com.example.rbac.db.repository.UserRoleRepository;
import com.example.rbac.web.request.UserRoleRequest;
import com.example.rbac.web.response.UserRoleResponse;

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


    public UserRoleResponse getUserRoleByUserId(Long userId) {
        if(userId == null) {
            logger.info("User id is null");
            return null;
        }
        if(!userRepository.existsByUserId(userId)) {
            logger.info("User does not exist");
            return null;
        }
        List<UserRole> userRoles = userRoleRepository.findByUserId(userId);
        if(userRoles == null || userRoles.isEmpty()) {
            logger.info("User does not have any roles");
            return null;
        }
        UserRoleResponse userRoleResponse = new UserRoleResponse();
        userRoleResponse.addAll(userRoles);
        return userRoleResponse;
    }


    public UserRoleResponse getUserRoleByRoleId(Long roleId) {
        if(roleId == null) {
            logger.info("Role id is null");
            return null;
        }
        if(!roleRepository.existsByRoleId(roleId)) {
            logger.info("Role does not exist");
            return null;
        }
        List<UserRole> userRoles = userRoleRepository.findByRoleId(roleId);
        if(userRoles == null || userRoles.isEmpty()) {
            logger.info("Role does not have any users");
            return null;
        }
        UserRoleResponse userRoleResponse = new UserRoleResponse();
        userRoleResponse.addAll(userRoles);
        return userRoleResponse;
    }
}
