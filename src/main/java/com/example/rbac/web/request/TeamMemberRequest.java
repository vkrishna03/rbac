package com.example.rbac.web.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamMemberRequest {
    
    private Long userId;
    
    private Long teamId;

    private Long accountId;
}
