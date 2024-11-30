package com.example.rbac.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbac.db.entity.Privilege;
import com.example.rbac.db.entity.Role;
import com.example.rbac.db.entity.mapper.RolePrivilege;
import com.example.rbac.db.repository.PrivilegeRepository;
import com.example.rbac.db.repository.RolePrivilegeRepository;
import com.example.rbac.db.repository.RoleRepository;
import com.example.rbac.enums.RoleType;
import com.example.rbac.web.request.RoleRequest;

import jakarta.annotation.PostConstruct;


@Service
public class RoleService {

	final Logger logger = LoggerFactory.getLogger(RoleService.class);
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RolePrivilegeRepository rolePrivilegeRepository;
	
	@PostConstruct
	public void init() {
		//TODO: Make sure mapInitialPrivileges runs only after roles are created.
		createInitialRoles();
		mapInitialPrivileges();
		
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
	
	public Role getRoleByRoleNameAndRoleType(String roleName, String roleType) {
        Optional<Role> role = roleRepository.findByRoleNameAndRoleType(roleName, roleType);
        if (role== null || role.isEmpty()) {
            logger.info("Role with name {} does not exist", roleName);
            return null;
        }
        return role.get();
    }
	
	public String mapRolePrivileges(long roleId, long privilegeId) {
		
		if(rolePrivilegeRepository.existsByRoleIdAndPrivilegeId(roleId, privilegeId)) {
			return "Already Exists";
		}
		
		RolePrivilege rolePrivilege = RolePrivilege.builder()
				.roleId(roleId)
				.privilegeId(privilegeId)
				.build();
		
		rolePrivilegeRepository.save(rolePrivilege);
		
		return "Okay";
	}
	
	
	public void createInitialRoles() {
		
		List<String> initAdminRoles = List.of("OWNER", "MANAGER");
		List<String> initAgentRoles = List.of("EMPLOYEE");
		
		for(String roleName: initAdminRoles) {
			roleRepository.findByRoleName(roleName).ifPresentOrElse(existingRoles -> {},() -> {
				Role role = Role.builder()
						.roleId(UUID.randomUUID().getMostSignificantBits())
						.roleName(roleName)
						.roleType(RoleType.ADMIN)
						.isCustomRole(false)
						.build();
				
				roleRepository.save(role);
			});
		}
		
		for(String roleName: initAgentRoles) {
			roleRepository.findByRoleName(roleName).ifPresentOrElse(existingRoles -> {},() -> {
				Role role = Role.builder()
						.roleId(UUID.randomUUID().getMostSignificantBits())
						.roleName(roleName)
						.roleType(RoleType.AGENT)
						.isCustomRole(false)
						.build();
				
				roleRepository.save(role);
			});
		}
	}
	
	
	public void mapInitialPrivileges() {
		logger.info("mapping privileges");
		List<String> actionNames = List.of("READ", "WRITE", "UPDATE", "DELETE");
		List<Role> systemRoles = roleRepository.findByIsCustomRole(false);
		
		for(String action: actionNames) {
			List<Privilege> privileges = privilegeRepository.findByAction(action);
			
			// Read privileges for every system role - SuperAdmin, Admin, Agent
			if(action == "READ") {
				for(Role role: systemRoles) {
					for(Privilege priv: privileges) {
						mapRolePrivileges(role.getRoleId(), priv.getPrivilegeId());
					}
				}
			}
			
			// Write and Update privileges for - Admin
			if(action == "WRITE" || action == "UPDATE") {
				for(Role role: systemRoles) {
					if(role.getRoleType() != RoleType.AGENT) {
						for(Privilege priv: privileges) {
							mapRolePrivileges(role.getRoleId(), priv.getPrivilegeId());
						}
					}
				}
			}
			
			// Delete Privileges for - SuperAdmin
//			if(action == "DELETE") {
//				roleRepository.findByRoleNameAndRoleType("SUPER_ADMIN", "SYSTEM")
//						.ifPresentOrElse(existingRole -> {
//							for(Privilege priv: privileges) {
//								mapRolePrivileges(existingRole.getRoleId(), priv.getPrivilegeId());
//							}
//						}, () -> {});
//			
//			}
			
		}
	}
}
