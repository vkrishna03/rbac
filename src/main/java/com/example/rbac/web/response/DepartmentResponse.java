package com.example.rbac.web.response;

import java.util.ArrayList;
import java.util.List;

import com.example.rbac.db.entity.Department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponse {
    
    private List<Department> departments;

    public void add(Department department) {
        if(departments == null) {
            departments = new ArrayList<>();
        }
        departments.add(department);
    }

    public void addAll(List<Department> departments2) {
        if(this.departments == null) {
            this.departments = new ArrayList<Department>();
        }
        this.departments.addAll(departments2);
    }
}
