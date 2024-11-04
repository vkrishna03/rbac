package com.example.rbac.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.rbac.db.entity.Privilege;
import com.example.rbac.db.repository.PrivilegeRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataInit {
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	@PostConstruct
	public void init() {
		
	
}
