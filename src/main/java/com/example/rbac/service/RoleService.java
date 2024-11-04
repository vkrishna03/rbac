package com.example.rbac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbac.db.repository.PrivilegeRepository;
import com.example.rbac.db.repository.RoleRepository;

import jakarta.annotation.PostConstruct;

@Service
public class RoleService {
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PostConstruct
	public void init() {
		createInitialRoles();
	}
	
	
	public void createInitialRoles() {
		// TODO: Implement logic
	}
}
