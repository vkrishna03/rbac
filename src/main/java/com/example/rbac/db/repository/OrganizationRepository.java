package com.example.rbac.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rbac.db.entity.Account;

@Repository
public interface OrganizationRepository extends JpaRepository<Account, Long> {

    Account findByAccountId(Long accountId);

}
