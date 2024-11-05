package com.example.rbac.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbac.db.entity.mapper.TeamMember;
import com.example.rbac.db.repository.TeamMemberRepository;
import com.example.rbac.db.repository.TeamRepository;
import com.example.rbac.db.repository.UserRepository;
import com.example.rbac.web.request.TeamMemberRequest;
import com.example.rbac.web.response.TeamMemberResponse;

@Service
public class TeamMemberService {
    
    final Logger logger = LoggerFactory.getLogger(TeamMemberService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    public TeamMember create(TeamMemberRequest teamMemberRequest) {
        if(teamMemberRequest == null) {
            logger.info("Team member request is null");
            return null;
        }
        if(!userRepository.existsByUserId(teamMemberRequest.getUserId())) {
            logger.info("User does not exist");
            return null;
        }
        if(!teamRepository.existsByTeamId(teamMemberRequest.getTeamId())) {
            logger.info("Team does not exist");
            return null;
        }
        TeamMember teamMember = TeamMember.builder()
                .userId(teamMemberRequest.getUserId())
                .teamId(teamMemberRequest.getTeamId())
                .accountId(teamMemberRequest.getAccountId())
                .build();
        return teamMemberRepository.save(teamMember);
    }


    public TeamMemberResponse getTeamMemberByUserId(long userId) {
        if(!userRepository.existsByUserId(userId)) {
            logger.info("User does not exist");
            return null;
        }
        List<TeamMember> teamMembers = teamMemberRepository.findByUserId(userId);
        if(teamMembers == null || teamMembers.isEmpty()) {
            logger.info("User does not have any team members");
            return null;
        }
        TeamMemberResponse teamMemberResponse = TeamMemberResponse.builder().build();
        teamMemberResponse.addAll(teamMembers);
        return teamMemberResponse;
    }


    public TeamMemberResponse getTeamMemberByTeamId(long teamId) {
        if(!teamRepository.existsByTeamId(teamId)) {
            logger.info("Team does not exist");
            return null;
        }
        List<TeamMember> teamMembers = teamMemberRepository.findByTeamId(teamId);
        if(teamMembers == null || teamMembers.isEmpty()) {
            logger.info("Team does not have any team members");
            return null;
        }
        TeamMemberResponse teamMemberResponse = TeamMemberResponse.builder().build();
        teamMemberResponse.addAll(teamMembers);
        return teamMemberResponse;
    }
}
