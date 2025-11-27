package com.project.sct.impl;

import com.project.sct.model.Complaint;
import com.project.sct.repository.ComplaintRepository;
import com.project.sct.service.ComplaintService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository rep;

    public ComplaintServiceImpl(ComplaintRepository rep) {
        this.rep = rep;
    }

    @Override
    public Complaint createComplaint(Complaint c) {
        return rep.save(c);
    }

    @Override
    public List<Complaint> listAll() {
        return rep.findAll();
    }

    @Override
    public List<Complaint> listByUser(Long userId) {
        return rep.findByUserId(userId);
    }

    @Override
    public Complaint findById(Long id) {
        return rep.findById(id).orElse(null);
    }

    @Override
    public void updateStatus(Long id, String status, String dept) {
        Complaint c = findById(id);
        if (c != null) {
            c.setStatus(status);
            if (dept != null)
                c.setAssignedDepartment(dept);
            rep.save(c);
        }
    }

    @Override
    public long countAll() {
        return rep.count();
    }

    @Override
    public long countByStatus(String status) {
        List<Complaint> list = rep.findByStatus(status);
        return list == null ? 0L : list.size();
    }
}
