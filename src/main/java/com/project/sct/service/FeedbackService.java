package com.project.sct.service;

import com.project.sct.model.Feedback;

public interface FeedbackService {
    Feedback saveFeedback(Feedback f);
    Feedback findById(Long id);
}
