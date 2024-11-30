package com.example.rbac.db.entity;

import com.example.rbac.enums.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
	@Column(nullable=false, unique=true)
	private long roleId;
	
	@Column(nullable=false)
	private String roleName;		// Manager, Employee, Team Lead
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private RoleType roleType;		// Admin, Agent, SuperAdmin 
	
	@Column(nullable=false)
	private boolean isCustomRole;	// System or Custom
	
	@Column
	private long accocuntId;	// NULL for common standard system roles like SuperAdmin, Admin and Agent. 

}
