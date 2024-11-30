package com.example.rbac.web.request;

import com.example.rbac.enums.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleRequest {

    private Long roleId;
    
    private String roleName;

    private RoleType roleType;

}
