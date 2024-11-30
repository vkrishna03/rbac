package com.example.rbac.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rbac.db.entity.PrivilegeGroup;

public interface PrivilegeGroupRepository extends JpaRepository<PrivilegeGroup, Long>{
	List<PrivilegeGroup> findBygroupName(String name);
}
