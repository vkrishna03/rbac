package com.example.rbac.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestAPI {

	@PostMapping("/{id}")
	public ResponseEntity<String> test(@PathVariable String id){
		if(id != null) {
			return ResponseEntity.ok("Test Successful: " + id);
		}
		return ResponseEntity.badRequest().build();
	}
}
