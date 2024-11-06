package com.example.rbac.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbac.db.entity.Privilege;
import com.example.rbac.db.repository.PrivilegeRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;

@Service
public class PrivilegeService {
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	@Autowired
	private ListableBeanFactory beanFactory;
	
	public static final List<String> actions = List.of("READ", "WRITE", "UPDATE", "DELETE");
	
	public String createPrivileges(List<String> action, String resourceName) {
		
		for(String actionName: action) {
			privilegeRepository.findByActionAndResource(actionName, resourceName).ifPresentOrElse(entities -> {},() -> {
                
				Privilege privilege = Privilege.builder()
						.privilegeId(UUID.randomUUID().getMostSignificantBits())
						.action(actionName).resource(resourceName).build();
				
                privilegeRepository.save(privilege);
            });
		}
		
		return "Okay";
	}
	
	public Optional<Privilege> getPrivilegeById(Long privilegeId) {
        return privilegeRepository.findById(privilegeId);
	}

    public void deletePrivilege(Long privilegeId) {
        privilegeRepository.deleteById(privilegeId);
    }
    
    public void createInitPrivilege() {
    	// Retrieve beans annotated with @Entity
        Map<String, Object> entities = beanFactory.getBeansWithAnnotation(Entity.class);
        
        entities.values().parallelStream().forEach(entity -> {
        	String resourceName = entity.getClass().getSimpleName();
        	String status = createPrivileges(actions,resourceName);
        });
        
    }
    
    @PostConstruct
    public void init() {
    	createInitPrivilege();
    }
    
    
}
