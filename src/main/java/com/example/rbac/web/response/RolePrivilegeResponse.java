package com.example.rbac.web.response;

import java.util.ArrayList;
import java.util.List;

import com.example.rbac.db.entity.mapper.RolePrivilege;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RolePrivilegeResponse {
    
    private List<RolePrivilege> rolePrivileges;

    public void add(RolePrivilege rolePrivilege) {
        if(this.rolePrivileges == null) {
            this.rolePrivileges = new ArrayList<RolePrivilege>();
        }
        this.rolePrivileges.add(rolePrivilege);
    }

    public void addAll(List<RolePrivilege> rolePrivileges2) {
        if(this.rolePrivileges == null) {
            this.rolePrivileges = new ArrayList<RolePrivilege>();
        }
        this.rolePrivileges.addAll(rolePrivileges2);
    }
}
