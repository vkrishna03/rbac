package com.example.rbac.db.entity;

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
public class Privilege {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
	@Column(nullable=false, unique=true)
	private long priviledgeId;
	
	@Column(nullable=false)
	private String action;	// CREATE, READ, UPDATE, DELETE
	
	@Column(nullable=false)
	private String resource;
}
