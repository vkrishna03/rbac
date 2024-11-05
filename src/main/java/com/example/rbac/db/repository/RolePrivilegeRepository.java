package com.example.rbac.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rbac.db.entity.mapper.RolePrivilege;

@Repository
public interface RolePrivilegeRepository extends JpaRepository<RolePrivilege, Long> {

    List<RolePrivilege> findByRoleId(Long roleId);

    List<RolePrivilege> findByPrivilegeId(Long privilegeId);
}
