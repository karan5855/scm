package com.project.sct.controller;

import com.project.sct.model.Complaint;
import com.project.sct.model.User;
import com.project.sct.service.ComplaintService;
import com.project.sct.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin("*")
public class ComplaintApiController {

    private final ComplaintService service;
    private final UserService userService;

    public ComplaintApiController(ComplaintService service , UserService userService){
        this.service = service;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createComplaint(@RequestBody Complaint payload){
        if (payload.getUser() == null || payload.getUser().getId() == null)
            return ResponseEntity.badRequest().body("USER_REQUIRED");
        User u = userService.findById(payload.getUser().getId());
        if (u == null)
            return ResponseEntity.badRequest().body("INVALID_USER");
        payload.setUser(u);
        Complaint saved = service.createComplaint(payload);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Complaint>> getByUser(@PathVariable Long id){
        return ResponseEntity.ok(service.listByUser(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Complaint>> getall(){
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getone(@PathVariable Long id){
        Complaint c = service.findById(id);
        if (c == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }

}
