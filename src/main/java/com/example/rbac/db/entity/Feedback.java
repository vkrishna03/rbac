package com.example.rbac.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
	@Column(nullable = false)
	private long feedbackId;
	
	@Column(nullable = false)
	private String description;
	
	@Column()
	private long accountId;
}
