package com.example.rbac.web.response;

import java.util.ArrayList;
import java.util.List;

import com.example.rbac.db.entity.mapper.TeamMember;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TeamMemberResponse {
    
    private List<TeamMember> teamMembers;

    public void add(TeamMember teamMember) {
        if(this.teamMembers == null) {
            this.teamMembers = new ArrayList<TeamMember>();
        }
        this.teamMembers.add(teamMember);
    }

    public void addAll(List<TeamMember> teamMembers2) {
        if(this.teamMembers == null) {
            this.teamMembers = new ArrayList<TeamMember>();
        }
        this.teamMembers.addAll(teamMembers2);
    }
}
