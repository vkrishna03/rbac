package com.example.rbac.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rbac.db.entity.mapper.TeamMember;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

    List<TeamMember> findByUserId(long userId);

    List<TeamMember> findByTeamId(long teamId);

    List<TeamMember> findByAccountId(Long accountId);

    List<TeamMember> findByUserIdAndTeamId(long userId, long teamId);

}
