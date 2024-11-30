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
	private PrivilegeGroupRepository privilegeGroupRepository;
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	public String createModule(String moduleName, List<String> groupNames) {
		
		long parentGroupId = ThreadLocalRandom.current().nextLong(1000000000L, 9999999999L);
		
		PrivilegeGroup modulePrivilegeGroup = PrivilegeGroup.builder()
				.privilegeGroupId(parentGroupId)
				.groupName(moduleName)
				.groupType(PrivilegeGroupType.MODULE)
				.parentGroupId(parentGroupId)
				.build();
		
		privilegeGroupRepository.save(modulePrivilegeGroup);
		
		for(String group: groupNames) {
			
			List<PrivilegeGroup> groups = privilegeGroupRepository.findBygroupName(group);
			
			groups.forEach(privilegeGroup -> {
				privilegeGroup.setParentGroupId(parentGroupId);
				privilegeGroupRepository.save(privilegeGroup);
			});
		}
		
		
		
		
		
		return "";
	}
	
public String createPrivilegeGroup(List<String> resources) {
		
		for(String resourceName: resources) {
		List<Privilege> privileges = privilegeRepository.findByResource(resourceName);
		
		long groupId = ThreadLocalRandom.current().nextLong(1000000000L, 9999999999L);
		
		PrivilegeGroup privilegeGroup = PrivilegeGroup.builder()
				.privilegeGroupId(groupId)
				.groupName(resourceName)
				.groupType(PrivilegeGroupType.FEATURE)
				.parentGroupId(groupId)
				.build();
		
		privilegeGroupRepository.save(privilegeGroup);
		
		
		privileges.forEach(privilege -> {
			privilege.setPrivilegeGroupId(privilegeGroup.getPrivilegeGroupId());
			privilegeRepository.save(privilege);
		});
		
		}
		return "";
	}
}
