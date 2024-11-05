package com.example.rbac.web.response;

import java.util.ArrayList;
import java.util.List;

import com.example.rbac.db.entity.Organization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationResponse {
    
    private List<Organization> organizations;

    public void add(Organization organization) {
        if(organizations == null) {
            organizations = new ArrayList<>();
        }
        organizations.add(organization);
    }

    public void addAll(List<Organization> organizations2) {
        if(this.organizations == null) {
            this.organizations = new ArrayList<Organization>();
        }
        this.organizations.addAll(organizations2);
    }
}
