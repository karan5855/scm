package com.project.sct.repository;

import com.project.sct.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback , Long> {
}
