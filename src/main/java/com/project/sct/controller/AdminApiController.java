package com.project.sct.controller;

import com.project.sct.model.Complaint;
import com.project.sct.model.User;
import com.project.sct.service.ComplaintService;
import com.project.sct.service.UserService;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminApiController {
    private final ComplaintService complaintService;
    private final UserService userService;

    public AdminApiController(ComplaintService complaintService, UserService userService){
        this.complaintService = complaintService;
        this.userService = userService;
    }

    @GetMapping("/complaints")
    public ResponseEntity<List<Complaint>> allComplaints(){
        return ResponseEntity.ok(complaintService.listAll());
    }

    @PostMapping("/complaints/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Map<String ,String> body){
        String status = body.get("status");
        String dept = body.get("assignedDepartment");
        Complaint c = complaintService.findById(id);
        if (c == null)
            return ResponseEntity.notFound().build();
        complaintService.updateStatus(id,status,dept);
        return ResponseEntity.ok("UPDATED");
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> listUsers(){
        return ResponseEntity.ok(userService.findAllUser());
    }
}
