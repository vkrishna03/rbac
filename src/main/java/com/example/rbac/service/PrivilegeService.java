package com.example.rbac.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbac.db.entity.Privilege;
import com.example.rbac.db.repository.PrivilegeRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.metamodel.EntityType;

@Service
public class PrivilegeService {
	
	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
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
    	try {
        // Get all managed entity types from JPA
        Set<EntityType<?>> entities = entityManagerFactory.getMetamodel().getEntities();
        System.out.println("Found entities: " + entities.size());
        
        entities.forEach(entityType -> {
            Class<?> entityClass = entityType.getJavaType();
            String resourceName = entityClass.getSimpleName();
            System.out.println("Processing entity: " + resourceName);
            String status = createPrivileges(actions, resourceName);
        });
    } catch (Exception e) {
        System.err.println("Error creating privileges: " + e.getMessage());
        e.printStackTrace();
    }
        
    }
    
    @PostConstruct
    public void init() {
    	createInitPrivilege();
    }
    
    
}
