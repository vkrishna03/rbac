package com.example.rbac.db.entity.mapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class DepartmentMember {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
	@Column(nullable = false)
	private long userId;
	
	@Column(nullable = false)
	private long departmentId;
	
	@Column
	private long accountId;
}
