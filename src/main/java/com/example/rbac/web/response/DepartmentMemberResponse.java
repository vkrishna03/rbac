package com.example.rbac.web.response;

import java.util.ArrayList;
import java.util.List;

import com.example.rbac.db.entity.mapper.DepartmentMember;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentMemberResponse {
    
    private List<DepartmentMember> departmentMembers;

    public void add(DepartmentMember departmentMember) {
        if(departmentMembers == null) {
            departmentMembers = new ArrayList<>();
        }
        departmentMembers.add(departmentMember);
    }

    public void addAll(List<DepartmentMember> departmentMembers2) {
        if(this.departmentMembers == null) {
            this.departmentMembers = new ArrayList<DepartmentMember>();
        }
        this.departmentMembers.addAll(departmentMembers2);
    }
}
