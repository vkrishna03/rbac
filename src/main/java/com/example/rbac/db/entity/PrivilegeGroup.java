package com.example.rbac.db.entity;

import com.example.rbac.enums.PrivilegeGroupType;

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
public class PrivilegeGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
	@Column(nullable=false, unique=true)
	private long privilegeGroupId;
	
	@Column(nullable=false, unique=true)
	private String groupName;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private PrivilegeGroupType groupType;		// Module, feature
	
	@Column(nullable=false)
	private long parentGroupId;
	
	@Column
	private long accountId;
}
