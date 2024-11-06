package com.example.rbac.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbac.db.entity.Role;
import com.example.rbac.db.repository.PrivilegeRepository;
import com.example.rbac.db.repository.RoleRepository;
import com.example.rbac.web.request.RoleRequest;

import jakarta.annotation.PostConstruct;


@Service
public class RoleService {

	final Logger logger = LoggerFactory.getLogger(RoleService.class);
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PostConstruct
	public void createInitialRoles() {
		
		List<String> initRoles = List.of("Super_Admin", "Admin", "Agent");
		
		for(String roleName: initRoles) {
			roleRepository.findByRoleName(roleName).ifPresentOrElse(existingRoles -> {},() -> {
				Role role = Role.builder()
						.roleId(UUID.randomUUID().getMostSignificantBits())
						.roleName(roleName)
						.roleType("SYSTEM")
						.build();
				
				roleRepository.save(role);
			});
		}
	}

	public Role create(RoleRequest roleRequest) {
		if(roleRequest == null ) {
			logger.info("Role request is null");
			return null;
		}
		Role role = Role.builder()
				.roleName(roleRequest.getRoleName())
				.roleType(roleRequest.getRoleType())
				.roleId(roleRequest.getRoleId())
				.build();
		return roleRepository.save(role);
	}


	public Role getRoleByRoleId(long roleId) {
        Optional<Role> role = roleRepository.findByRoleId(roleId);
        if (role == null || role.isEmpty()) {
            logger.info("Role with ID {} does not exist", roleId);
            return null;
        }
        return role.get();
    }

	public Role getRoleByRoleName(String roleName) {
        Optional<Role> role = roleRepository.findByRoleName(roleName);
        if (role== null || role.isEmpty()) {
            logger.info("Role with name {} does not exist", roleName);
            return null;
        }
        return role.get();
    }
}
