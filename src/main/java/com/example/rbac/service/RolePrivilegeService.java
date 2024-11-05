package com.example.rbac.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbac.db.entity.mapper.RolePrivilege;
import com.example.rbac.db.repository.PrivilegeRepository;
import com.example.rbac.db.repository.RolePrivilegeRepository;
import com.example.rbac.db.repository.RoleRepository;
import com.example.rbac.web.response.RolePrivilegeResponse;

@Service
public class RolePrivilegeService {
    
    final Logger logger = LoggerFactory.getLogger(RolePrivilegeService.class);

    @Autowired
    private RolePrivilegeRepository rolePrivilegeRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private RoleRepository roleRepository;

    public RolePrivilege create(Long roleId, Long privilegeId) {
        if(roleId == null || privilegeId == null) {
            logger.info("Role or privilege id is null");
            return null;
        }
        if(!roleRepository.existsByRoleId(roleId) || !privilegeRepository.existsByPrivilegeId(privilegeId)) {
            logger.info("Role or privilege does not exist");
            return null;
        }
        RolePrivilege rolePrivilege = new RolePrivilege();
        rolePrivilege.setRoleId(roleId);
        rolePrivilege.setPrivilegeId(privilegeId);
        return rolePrivilegeRepository.save(rolePrivilege);
    }

    public RolePrivilegeResponse getPrivilegesByRoleId(Long roleId) {
        if(roleId == null || !roleRepository.existsByRoleId(roleId)) {
            logger.info("Role does not exist");
            return null;
        }
        List<RolePrivilege> rolePrivileges = rolePrivilegeRepository.findByRoleId(roleId);
        if(rolePrivileges == null || rolePrivileges.isEmpty()) {
            logger.info("Role does not have any privileges");
            return null;
        }
        RolePrivilegeResponse rolePrivilegeResponse = RolePrivilegeResponse.builder().build();
        rolePrivilegeResponse.addAll(rolePrivileges);
        return rolePrivilegeResponse;
    }

    public RolePrivilegeResponse getPrivilegesByPrivilegeId(Long privilegeId) {
        if(privilegeId == null || !privilegeRepository.existsByPrivilegeId(privilegeId)) {
            logger.info("Privilege does not exist");
            return null;
        }
        List<RolePrivilege> rolePrivileges = rolePrivilegeRepository.findByPrivilegeId(privilegeId);
        if(rolePrivileges == null || rolePrivileges.isEmpty()) {
            logger.info("Privilege does not have any roles");
            return null;
        }
        RolePrivilegeResponse rolePrivilegeResponse = RolePrivilegeResponse.builder().build();
        rolePrivilegeResponse.addAll(rolePrivileges);
        return rolePrivilegeResponse;
    }
}
