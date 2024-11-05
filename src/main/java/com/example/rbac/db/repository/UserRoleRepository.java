package com.example.rbac.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rbac.db.entity.mapper.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}