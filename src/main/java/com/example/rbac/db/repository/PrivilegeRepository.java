package com.example.rbac.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rbac.db.entity.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long>{
	Optional<Privilege> findByActionAndResource(String action, String resource);

    boolean existsByPrivilegeId(Long privilegeId);
}
