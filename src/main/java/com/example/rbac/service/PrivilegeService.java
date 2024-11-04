package com.example.rbac.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbac.db.entity.Privilege;
import com.example.rbac.db.repository.PrivilegeRepository;

@Service
public class PrivilegeService {
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	public String createPrivilege(String action, String resource) {
		Privilege privilege = Privilege.builder()
				.action(action).resource(resource).build();
		
		privilegeRepository.save(privilege);
		
		return "Okay";
	}
	
	public Optional<Privilege> getPrivilegeById(Long privilegeId) {
        return privilegeRepository.findById(privilegeId);
	}

    public void deletePrivilege(Long privilegeId) {
        privilegeRepository.deleteById(privilegeId);
    }
}
