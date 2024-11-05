package com.example.rbac.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbac.db.entity.Team;
import com.example.rbac.db.repository.TeamRepository;
import com.example.rbac.web.response.TeamResponse;

@Service
public class TeamService {
    
    final Logger logger = LoggerFactory.getLogger(TeamService.class);

    @Autowired
    private TeamRepository teamRepository;

    public Team create(Team team) {
        if(team == null) {
            logger.info("Team is null");
            return null;
        }
        if(!teamRepository.existsByTeamId(team.getParentTeamID())) {
            logger.info("Parent Team does not exist");
            return null;
        }
        return teamRepository.save(team);
    }

    public TeamResponse getTeamByTeamId(Long teamId) {
        if(teamId == null) {
            logger.info("Team id is null");
            return null;
        }
        Team team = teamRepository.findByTeamId(teamId);
        if(team == null) {
            logger.info("Team does not exist");
            return null;
        }
        TeamResponse teamResponse = new TeamResponse();
        teamResponse.add(team);
        return teamResponse;
    }

    public TeamResponse getTeamByParentId(Long parentId) {
        if(parentId == null) {
            logger.info("Parent id is null");
            return null;
        }
        List<Team> teams = teamRepository.findByParentTeamID(parentId);
        if(teams == null || teams.isEmpty()) {
            logger.info("Team does not exist");
            return null;
        }
        TeamResponse teamResponse = new TeamResponse();
        teamResponse.addAll(teams);
        return teamResponse;
    }

    public TeamResponse getTeamByAccountId(Long accountId) {
        if(accountId == null) {
            logger.info("Account id is null");
            return null;
        }
        List<Team> teams = teamRepository.findByAccountId(accountId);
        if(teams == null || teams.isEmpty()) {
            logger.info("Team does not exist");
            return null;
        }
        TeamResponse teamResponse = new TeamResponse();
        teamResponse.addAll(teams);
        return teamResponse;
    }
}
