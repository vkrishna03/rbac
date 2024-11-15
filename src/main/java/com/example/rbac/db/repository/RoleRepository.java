package com.example.rbac.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rbac.db.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByRoleName(String name);

	boolean existsByRoleId(Long roleId);

    Optional<Role> findByRoleId(long roleId);
    
    List<Role> findByRoleType(String roleType);
    
    Optional<Role> findByRoleNameAndRoleType(String roleName, String roleType);
}
