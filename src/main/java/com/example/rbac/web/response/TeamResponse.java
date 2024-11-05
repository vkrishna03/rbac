package com.example.rbac.web.response;

import java.util.ArrayList;
import java.util.List;

import com.example.rbac.db.entity.Team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponse {
    
    private List<Team> teams;

    public void add(Team team) {
        if(teams == null) {
            teams = new ArrayList<>();
        }
        teams.add(team);
    }

    public void addAll(List<Team> teams2) {
        if(this.teams == null) {
            this.teams = new ArrayList<Team>();
        }
        this.teams.addAll(teams2);
    }
}
