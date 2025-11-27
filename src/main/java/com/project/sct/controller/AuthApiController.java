package com.project.sct.controller;

import com.project.sct.model.User;
import com.project.sct.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Chodina MVC ma kon Rest Vapre..
 *
 * @author Denish Kotadiya
 */
@Controller
@RequestMapping("/api/auth")
public class AuthApiController {

    private static final Logger log = LoggerFactory.getLogger(AuthApiController.class);
    private final UserService userService;

    public AuthApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {

        log.info("Start Login Process.. Login Detail:: email={}", email);

        if (email.trim().isEmpty() || password.trim().isEmpty()) {
            model.addAttribute("msg", "Email and Password are required");
            return "login";
        }

        var opt = userService.findByEmail(email);

        if (opt.isEmpty()) {
            model.addAttribute("msg", "User not found!");
            return "login";
        }

        User user = opt.get();

        if (!user.getPassword().equals(password)) {
            model.addAttribute("msg", "Invalid password!");
            return "login";
        }

        // ✅ SUCCESS
        return "redirect:/api/auth/register";
    }



    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user, org.springframework.ui.Model model) {

        log.info("Start register API:: user: {}", user);
        // validation
        if (user.getEmail() == null || user.getPassword() == null) {
            model.addAttribute("msg", "Email and Password are required");
            return "register";   // reload register.html
        }

        // check email exists
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("msg", "Email already exists!");
            return "register";
        }

        // save user
        user.setRole("USER");
        userService.save(user);

        model.addAttribute("msg", "Registration successful. Please login.");

        return "redirect:/api/auth/login";
    }

}
