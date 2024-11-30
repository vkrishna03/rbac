package com.example.rbac.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rbac.db.entity.PrivilegeGroup;

public interface PrivilegeGroupRepository extends JpaRepository<PrivilegeGroup, Long>{

}
