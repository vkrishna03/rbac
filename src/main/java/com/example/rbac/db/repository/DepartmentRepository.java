package com.example.rbac.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rbac.db.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
