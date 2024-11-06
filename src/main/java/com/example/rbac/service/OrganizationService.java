package com.example.rbac.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbac.db.entity.Organization;
import com.example.rbac.db.repository.OrganizationRepository;
import com.example.rbac.web.response.OrganizationResponse;

@Service
public class OrganizationService {
    
    final Logger logger = LoggerFactory.getLogger(OrganizationService.class);

    @Autowired
    private OrganizationRepository organizationRepository;

    public Organization create(Organization organization) {
        if(organization == null) {
            logger.info("Organization is null");
            return null;
        }
        return organizationRepository.save(organization);
    }

    public OrganizationResponse getOrganizationByOrganizationId(Long organizationId) {
        if(organizationId == null) {
            logger.info("Organization id is null");
            return null;
        }
        Organization organization = organizationRepository.findByAccountId(organizationId);
        if(organization == null) {
            logger.info("Organization does not exist");
            return null;
        }
        OrganizationResponse organizationResponse = OrganizationResponse.builder().build();
        organizationResponse.add(organization);
        return organizationResponse;
    }
}
