package com.project.sct.impl;

import com.project.sct.model.Feedback;
import com.project.sct.repository.FeedbackRepository;
import com.project.sct.service.FeedbackService;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository repo;
    public FeedbackServiceImpl(FeedbackRepository repo){
        this.repo = repo;
    }

    @Override
    public Feedback saveFeedback(Feedback f){
        return repo.save(f);
    }

    @Override
    public Feedback findById(Long id){
        return repo.findById(id).orElse(null);
    }

}
