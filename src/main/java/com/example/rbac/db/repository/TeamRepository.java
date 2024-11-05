package com.example.rbac.db.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rbac.db.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    boolean existsByTeamId(Long teamId);

    Team findByTeamId(Long teamId);

    List<Team> findByAccountId(Long accountId);

    List<Team> findByParentTeamID(Long parentId);

}
