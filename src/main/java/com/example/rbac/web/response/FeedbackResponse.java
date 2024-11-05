package com.example.rbac.web.response;

import java.util.ArrayList;
import java.util.List;

import com.example.rbac.db.entity.Feedback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackResponse {
    
    private List<Feedback> feedbacks;

    public void add(Feedback feedback) {
        if(feedbacks == null) {
            feedbacks = new ArrayList<>();
        }
        feedbacks.add(feedback);
    }

    public void addAll(List<Feedback> feedbacks2) {
        if(this.feedbacks == null) {
            this.feedbacks = new ArrayList<Feedback>();
        }
        this.feedbacks.addAll(feedbacks2);
    }
}
