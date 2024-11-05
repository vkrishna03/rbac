package com.example.rbac.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbac.db.entity.Department;
import com.example.rbac.db.repository.DepartmentRepository;
import com.example.rbac.web.response.DepartmentResponse;

@Service
public class DepartmentService {
    
    final Logger logger = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private DepartmentRepository departmentRepository;


    public Department create(Department department) {
        if(department == null) {
            logger.info("Department is null");
            return null;
        }
        if(!departmentRepository.existsByDepartmentId(department.getParentDepartmentID())) {
            logger.info("Parent department does not exist");
            return null;
        }
        return departmentRepository.save(department);
    }


    public DepartmentResponse getDepartmentByDepartmentId(Long departmentId) {
        if(departmentId == null) {
            logger.info("Department id is null");
            return null;
        }
        Department department = departmentRepository.findByDepartmentId(departmentId);
        if(department == null) {
            logger.info("Department does not exist");
            return null;
        }
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.add(department);
        return departmentResponse;
    }

    public DepartmentResponse getDapartmentByParentId(Long parentId) {
        if(parentId == null) {
            logger.info("Parent id is null");
            return null;
        }
        List<Department> departments = departmentRepository.findByParentDepartmentID(parentId);
        if(departments == null || departments.isEmpty()) {
            logger.info("Department does not exist");
            return null;
        }
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.addAll(departments);
        return departmentResponse;
    }

    public DepartmentResponse getAllDepartmentByAccountId(Long accountId) {
        if(accountId == null) {
            logger.info("Account id is null");
            return null;
        }
        List<Department> departments = departmentRepository.findByAccountId(accountId);
        if(departments == null || departments.isEmpty()) {
            logger.info("Department does not exist");
            return null;
        }
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.addAll(departments);
        return departmentResponse;
    }
}
