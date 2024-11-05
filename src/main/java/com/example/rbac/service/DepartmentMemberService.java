package com.example.rbac.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbac.db.entity.mapper.DepartmentMember;
import com.example.rbac.db.repository.DepartmentMemberRepository;
import com.example.rbac.db.repository.DepartmentRepository;
import com.example.rbac.db.repository.UserRepository;
import com.example.rbac.web.response.DepartmentMemberResponse;

@Service
public class DepartmentMemberService {
    
    final Logger logger = LoggerFactory.getLogger(DepartmentMemberService.class);

    @Autowired
    private DepartmentMemberRepository departmentMemberRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;


    public DepartmentMember create(Long userId, Long departmentId) {
        if(userId == null || departmentId == null) {
            logger.info("User id or department id is null");
            return null;
        }
        if(!userRepository.existsByUserId(userId)) {
            logger.info("User does not exist");
            return null;
        }
        if(!departmentRepository.existsByDepartmentId(departmentId)) {
            logger.info("Department does not exist");
            return null;
        }
        return departmentMemberRepository.save(DepartmentMember.builder()
                .userId(userId)
                .departmentId(departmentId)
                .build());
    }

    public DepartmentMemberResponse getDepartmentMemberByUserId(Long userId) {
        if(userId == null) {
            logger.info("User id is null");
            return null;
        }
        if(!userRepository.existsByUserId(userId)) {
            logger.info("User does not exist");
            return null;
        }
        List<DepartmentMember> departmentMembers = departmentMemberRepository.findByUserId(userId);
        if(departmentMembers == null || departmentMembers.isEmpty()) {
            logger.info("Department member does not exist");
            return null;
        }
        DepartmentMemberResponse departmentMemberResponse = new DepartmentMemberResponse();
        departmentMemberResponse.addAll(departmentMembers);
        return departmentMemberResponse;
    }

    public DepartmentMemberResponse getDepartmentMemberByDepartmentId(Long departmentId) {
        if(departmentId == null) {
            logger.info("Department id is null");
            return null;
        }
        if(!departmentRepository.existsByDepartmentId(departmentId)) {
            logger.info("Department does not exist");
            return null;
        }
        List<DepartmentMember> departmentMembers = departmentMemberRepository.findByDepartmentId(departmentId);
        if(departmentMembers == null || departmentMembers.isEmpty()) {
            logger.info("Department member does not exist");
            return null;
        }
        DepartmentMemberResponse departmentMemberResponse = new DepartmentMemberResponse();
        departmentMemberResponse.addAll(departmentMembers);
        return departmentMemberResponse;
    }

    public DepartmentMemberResponse getDepartmentMemberByUserIdAndDepartmentId(Long userId, Long departmentId) {
        if(userId == null || departmentId == null) {
            logger.info("User id or department id is null");
            return null;
        }
        if(!userRepository.existsByUserId(userId)) {
            logger.info("User does not exist");
            return null;
        }
        if(!departmentRepository.existsByDepartmentId(departmentId)) {
            logger.info("Department does not exist");
            return null;
        }
        List<DepartmentMember> departmentMembers = departmentMemberRepository.findByUserIdAndDepartmentId(userId, departmentId);
        if(departmentMembers == null || departmentMembers.isEmpty()) {
            logger.info("Department member does not exist");
            return null;
        }
        DepartmentMemberResponse departmentMemberResponse = new DepartmentMemberResponse();
        departmentMemberResponse.addAll(departmentMembers);
        return departmentMemberResponse;
    }

    public DepartmentMemberResponse getDepartmentMemberByAccountId(Long accountId) {
        if(accountId == null) {
            logger.info("Account id is null");
            return null;
        }
        List<DepartmentMember> departmentMembers = departmentMemberRepository.findByAccountId(accountId);
        if(departmentMembers == null || departmentMembers.isEmpty()) {
            logger.info("Department member does not exist");
            return null;
        }
        DepartmentMemberResponse departmentMemberResponse = new DepartmentMemberResponse();
        departmentMemberResponse.addAll(departmentMembers);
        return departmentMemberResponse;
    }
}
