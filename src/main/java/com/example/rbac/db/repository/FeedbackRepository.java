package com.example.rbac.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rbac.db.entity.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    Feedback findByFeedbackId(Long feedbackId);

    List<Feedback> findByAccountId(Long accountId);

}
