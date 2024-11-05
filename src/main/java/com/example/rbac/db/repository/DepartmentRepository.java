package com.example.rbac.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rbac.db.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    boolean existsByDepartmentId(long departmentId);

    Department findByDepartmentId(Long departmentId);

    List<Department> findByParentDepartmentID(Long parentId);

    List<Department> findByAccountId(Long accountId);

}
