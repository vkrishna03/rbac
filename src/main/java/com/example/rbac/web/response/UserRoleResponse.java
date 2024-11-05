package com.example.rbac.web.response;

import java.util.ArrayList;
import java.util.List;

import com.example.rbac.db.entity.mapper.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoleResponse {
    
    private List<UserRole> userRoles;

    public void add(UserRole userRole) {
        if(this.userRoles == null) {
            this.userRoles = new ArrayList<UserRole>();
        }
        this.userRoles.add(userRole);
    }

    public void addAll(List<UserRole> userRoles2) {
        if(this.userRoles == null) {
            this.userRoles = new ArrayList<UserRole>();
        }
        this.userRoles.addAll(userRoles2);
    }
}
