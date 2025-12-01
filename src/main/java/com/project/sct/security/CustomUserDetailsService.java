package com.project.sct.security;

import com.project.sct.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  private final UserRepository repo;
  public CustomUserDetailsService(UserRepository repo) { this.repo = repo; }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = repo.findByEmail(username)
      .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
    return User.withUsername(user.getEmail())
               .password(user.getPassword()) // already encoded
               .authorities(user.getRole())
               .build();
  }

}
