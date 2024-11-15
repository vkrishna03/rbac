package com.example.rbac.db.entity;

import jakarta.persistence.Entity;
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
public class Account {
	
	@Id
	private long id;
	
	private long accountId;
	
	private String accountName;
	
	private long userId;
}
