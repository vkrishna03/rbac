package com.example.rbac.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbac.db.entity.Feedback;
import com.example.rbac.db.repository.FeedbackRepository;
import com.example.rbac.web.response.FeedbackResponse;

@Service
public class FeedbackService {
    
    final Logger logger = LoggerFactory.getLogger(FeedbackService.class);

    @Autowired
    private FeedbackRepository feedbackRepository;


    public Feedback create(Feedback feedback) {
        if(feedback == null) {
            logger.info("Feedback is null");
            return null;
        }
        return feedbackRepository.save(feedback);
    }


    public FeedbackResponse getFeedbackByFeedbackId(Long feedbackId) {
        if(feedbackId == null) {
            logger.info("Feedback id is null");
            return null;
        }
        Feedback feedback = feedbackRepository.findByFeedbackId(feedbackId);
        if(feedback == null) {
            logger.info("Feedback does not exist");
            return null;
        }
        FeedbackResponse feedbackResponse = new FeedbackResponse();
        feedbackResponse.add(feedback);
        return feedbackResponse;
    }

    public FeedbackResponse getFeedbackByAccountId(Long accountId) {
        if(accountId == null) {
            logger.info("Account id is null");
            return null;
        }
        List<Feedback> feedbacks = feedbackRepository.findByAccountId(accountId);
        if(feedbacks == null || feedbacks.isEmpty()) {
            logger.info("Feedback does not exist");
            return null;
        }
        FeedbackResponse feedbackResponse = new FeedbackResponse();
        feedbackResponse.addAll(feedbacks);
        return feedbackResponse;
    }
}
