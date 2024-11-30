package com.example.rbac.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature")
public class FeatureAPI {

	// anyone
		@GetMapping("/{id}")
		public ResponseEntity<String> test(@PathVariable String id){
			if(id != null) {
				return ResponseEntity.ok("Test Successful: " + id);
			}
			return ResponseEntity.badRequest().build();
		}
}
