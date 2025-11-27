package com.project.sct.service;

import com.project.sct.model.Complaint;

import java.util.List;

public interface ComplaintService {
    Complaint createComplaint(Complaint c);
    List<Complaint> listAll();
    List<Complaint> listByUser(Long userId);
    Complaint findById(Long id);
    void updateStatus(Long id, String status, String dept);
    long countAll();
    long countByStatus(String status);
}
