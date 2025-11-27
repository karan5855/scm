package com.project.sct.controller;

import com.project.sct.model.Feedback;
import com.project.sct.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin("*")
public class FeedbackApiController {
    private final FeedbackService feedbackService;
    public FeedbackApiController(FeedbackService feedbackService){
        this.feedbackService = feedbackService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Feedback f){
        if (f.getComplaint() == null || f.getComplaint().getId() == null)
            return ResponseEntity.badRequest().body("COMPLAINT_REQUIRED");
        Feedback saved = feedbackService.saveFeedback(f);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        Feedback f = feedbackService.findById(id);
        if (f == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(f);
    }
}
