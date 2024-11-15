package com.example.rbac.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbac.db.entity.Role;
import com.example.rbac.db.entity.User;
import com.example.rbac.db.entity.mapper.UserRole;
import com.example.rbac.db.repository.UserRepository;
import com.example.rbac.db.repository.UserRoleRepository;
import com.example.rbac.security.jwt.JwtUtil;

@Service
public class UserService {

	final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	public String createUser(User user) {
		if(user == null) {
			logger.info("User is null");
			return null;
		}
		
		if(!userRepository.existsByUserId(user.getUserId())) {
			String jwt = jwtUtil.generateToken(user.getUsername());
			
			userRepository.save(user);
			
			return jwt;
		}
		
		logger.info("User already exists");
		
		return null;
	}
	
	public String mapRole(String username, String roleName, String roleType) {
		
		Role role = roleService.getRoleByRoleNameAndRoleType(roleName, roleType);
		User user = userRepository.findByUsername(username).get();
		
		if(!userRoleRepository.existsByUserIdAndRoleId(user.getUserId(), role.getRoleId())) {
			
			UserRole userRole = UserRole.builder()
					.userId(user.getUserId())
					.roleId(role.getRoleId())
					.build();
			
			userRoleRepository.save(userRole);
			
			String jwt = refreshToken(username);
			
			return jwt;
		}
		
		logger.info("User or role does not exist");
		
		return null;
	}
	
	public String refreshToken(String username) {
		

			User user = userRepository.findByUsername(username).get();
			
			if(user != null) {
				
				List<UserRole> roles = userRoleRepository.findByUserId(user.getUserId());
				
				List<Long> roleIds = new ArrayList<>();
				
				for(UserRole role: roles) {
					roleIds.add(role.getRoleId());
				}
				
				String jwt = jwtUtil.generateTokenWithRoles(user.getUsername(), roleIds);
				
				
				return jwt;
				
			}
			
			
		
		
		
		
		
		return "User does not exist";
	}
	
}
