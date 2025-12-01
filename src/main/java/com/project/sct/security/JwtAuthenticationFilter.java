package com.project.sct.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final CustomUserDetailsService userService;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
          throws ServletException, IOException {

    var cookies = req.getCookies();
    String token = null;

    if (cookies != null) {
      for (Cookie c : cookies) {
        if (c.getName().equals("jwt")) {
          token = c.getValue();
        }
      }
    }

    if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      String email = jwtService.extractEmail(token);
      var user = userService.loadUserByUsername(email);

      var auth =
              new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(auth);
    }

    chain.doFilter(req, res);
  }
}