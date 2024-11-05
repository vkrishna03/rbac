package com.example.rbac.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rbac.db.entity.mapper.DepartmentMember;

@Repository
public interface DepartmentMemberRepository extends JpaRepository<DepartmentMember, Long> {

    List<DepartmentMember> findByUserId(Long userId);

    List<DepartmentMember> findByDepartmentId(Long departmentId);

    List<DepartmentMember> findByUserIdAndDepartmentId(Long userId, Long departmentId);

    List<DepartmentMember> findByAccountId(Long accountId);

}
