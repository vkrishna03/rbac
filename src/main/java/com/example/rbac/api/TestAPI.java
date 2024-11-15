package com.example.rbac.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rbac.db.entity.User;
import com.example.rbac.service.UserService;

@RestController
@RequestMapping("/test")
public class TestAPI {
	
	@Autowired
	private UserService userService;

	// anyone
	@GetMapping("/{id}")
	public ResponseEntity<String> test(@PathVariable String id){
		if(id != null) {
			return ResponseEntity.ok("Test Successful: " + id);
		}
		return ResponseEntity.badRequest().build();
	}
	
	
	@GetMapping("/user")
	public ResponseEntity<Map<String, String>> test(){
			
			List<String> users = List.of("dev@mail.in", "admin@mail.in", "super@mail.in");
			
			Map<String, String> map = new HashMap<>();
			
			int i = 9640;
			
			for(String username: users) {
				User user = User.builder()
						.userId(i+1)
						.username(username)
						.password("password")
						.build();
				
				String jwt = userService.createUser(user);
				map.put(username, jwt);
				i++;
			}
			
			String jwt = userService.mapRole("dev@mail.in", "AGENT", "SYSTEM");
			map.put("dev@mail.in", jwt);
			
			jwt = userService.mapRole("admin@mail.in", "ADMIN", "SYSTEM");
			map.put("admin@mail.in", jwt);
			
			
			jwt = userService.mapRole("super@mail.in", "SUPER_ADMIN", "SYSTEM");
			map.put("super@mail.in", jwt);
			
			return ResponseEntity.ok(map);

	}
}
