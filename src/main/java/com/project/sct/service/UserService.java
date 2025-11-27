package com.project.sct.service;

import com.project.sct.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findByEmail(String email);
    User findById(Long id);
    List<User> findAllUser();
}

