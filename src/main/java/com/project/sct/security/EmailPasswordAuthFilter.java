package com.project.sct.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class EmailPasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
        String email = req.getParameter("email");     // instead of username
        String password = req.getParameter("password");

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(email, password);

        return this.getAuthenticationManager().authenticate(auth);
    }
}
