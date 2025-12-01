package com.project.sct.controller;

import com.project.sct.model.Complaint;
import com.project.sct.model.User;
import com.project.sct.service.ComplaintService;
import com.project.sct.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/complaints")
public class ComplaintApiController {

    private final ComplaintService service;
    private final UserService userService;

    public ComplaintApiController(ComplaintService service , UserService userService){
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createComplaintPage(Model model) {

        model.addAttribute("complaint", new Complaint());

        // Avoid null pointer
        List<User> users = userService.findAllUser();
        if (users == null) {
            users = List.of(); // empty list avoids Thymeleaf crash
        }
        model.addAttribute("users", users);

        return "complaint/complaint-create";
    }

    @PostMapping("/create")
    public String createComplaint(@ModelAttribute Complaint complaint, Model model) {

        if (complaint.getUser() == null || complaint.getUser().getId() == null) {
            model.addAttribute("msg", "User is required!");
            return "complaint/complaint-create";
        }

        User u = userService.findById(complaint.getUser().getId());
        if (u == null) {
            model.addAttribute("msg", "Invalid user!");
            return "complaint/complaint-create";
        }

        complaint.setUser(u);
        service.createComplaint(complaint);

        model.addAttribute("msg", "Complaint submitted successfully!");
        return "redirect:/complaints/list";
    }

    @GetMapping("/user/{id}")
    public String getByUser(@PathVariable Long id, Model model) {
        List<Complaint> complaints = service.listByUser(id);
        model.addAttribute("complaints", complaints);
        return "complaint/complaint-user-list";
    }

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("complaints", service.listAll());
        return "complaint/complaint-list";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id, Model model) {
        Complaint c = service.findById(id);
        if (c == null) {
            model.addAttribute("msg", "Complaint not found!");
            return "complaint/complaint-list";
        }
        model.addAttribute("complaint", c);
        return "complaint/complaint-detail";
    }

}
