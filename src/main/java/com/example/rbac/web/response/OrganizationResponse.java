package com.example.rbac.web.response;

import java.util.ArrayList;
import java.util.List;

import com.example.rbac.db.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationResponse {
    
    private List<Account> organizations;

    public void add(Account organization) {
        if(organizations == null) {
            organizations = new ArrayList<>();
        }
        organizations.add(organization);
    }

    public void addAll(List<Account> organizations2) {
        if(this.organizations == null) {
            this.organizations = new ArrayList<Account>();
        }
        this.organizations.addAll(organizations2);
    }
}
