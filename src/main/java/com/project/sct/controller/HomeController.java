package com.project.sct.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "user-dashboard";
    }

    @GetMapping("/")
    public String home() {
        return "home";  // loads home.html
    }

}
