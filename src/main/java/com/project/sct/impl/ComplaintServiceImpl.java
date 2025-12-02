package com.project.sct.impl;

import com.project.sct.model.Complaint;
import com.project.sct.repository.ComplaintRepository;
import com.project.sct.service.ComplaintService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Override
    public Complaint createComplaint(Complaint c) {
        return complaintRepository.save(c);
    }

    @Override
    public List<Complaint> listAll() {
        return complaintRepository.findAll();
    }

    @Override
    public List<Complaint> listByUser(Long userId) {
        return complaintRepository.findByUserId(userId);
    }

    @Override
    public Complaint findById(Long id) {
        return complaintRepository.findById(id).orElse(null);
    }

    @Override
    public void updateStatus(Long id, String status, String dept) {
        Complaint c = findById(id);
        if (c != null) {
            c.setStatus(status);
            if (dept != null)
                c.setAssignedDepartment(dept);
            complaintRepository.save(c);
        }
    }

    @Override
    public long countAll() {
        return complaintRepository.count();
    }

    @Override
    public long countByStatus(String status) {
        List<Complaint> list = complaintRepository.findByStatus(status);
        return list == null ? 0L : list.size();
    }

    @Override
    public void delete(Long id) {
        complaintRepository.deleteById(id);
    }
}
