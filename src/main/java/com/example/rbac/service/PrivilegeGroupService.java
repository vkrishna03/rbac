package com.example.rbac.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbac.db.entity.Privilege;
import com.example.rbac.db.entity.PrivilegeGroup;
import com.example.rbac.db.repository.PrivilegeGroupRepository;
import com.example.rbac.db.repository.PrivilegeRepository;
import com.example.rbac.enums.PrivilegeGroupType;


@Service
public class PrivilegeGroupService {

	@Autowired
	private PrivilegeGroupRepository privilegeGroupRepositoy;
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	public String createPrivilegeGroupForModule(String moduleName,long groupId, String resourceName) {
		
		List<Privilege> privileges = privilegeRepository.findByResource(resourceName);
		
		PrivilegeGroup privilegeGroup = PrivilegeGroup.builder()
				.privilegeGroupId(ThreadLocalRandom.current().nextLong(1000000000L, 9999999999L))
				.groupName(moduleName)
				.groupType(PrivilegeGroupType.MODULE)
				.parentGroupId(groupId)
				.build();
		
		
		privileges.forEach(privilege -> {
			privilege.setPrivilegeGroupId(privilegeGroup.getPrivilegeGroupId());
		});
		
		return "";
	}
	
public String createPrivilegeGroup(List<String> resources) {
		
		for(String resourceName: resources) {
		List<Privilege> privileges = privilegeRepository.findByResource(resourceName);
		System.out.println(privileges);
		
		long groupId = ThreadLocalRandom.current().nextLong(1000000000L, 9999999999L);
		
		PrivilegeGroup privilegeGroup = PrivilegeGroup.builder()
				.privilegeGroupId(groupId)
				.groupName(resourceName)
				.groupType(PrivilegeGroupType.FEATURE)
				.parentGroupId(groupId)
				.build();
		
		privilegeGroupRepositoy.save(privilegeGroup);
		
		
		privileges.forEach(privilege -> {
			privilege.setPrivilegeGroupId(privilegeGroup.getPrivilegeGroupId());
			privilegeRepository.save(privilege);
		});
		
		}
		return "";
	}
}
