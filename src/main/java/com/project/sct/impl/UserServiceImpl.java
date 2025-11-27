package com.project.sct.impl;

import com.project.sct.model.User;
import com.project.sct.repository.UserRepository;
import com.project.sct.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository rep;

    public UserServiceImpl(UserRepository rep) {
        this.rep = rep;
    }

    @Override
    public User save(User user) {
        return rep.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return rep.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return rep.findById(id).orElse(null);
    }

    @Override
    public List<User> findAllUser() {
        return rep.findAll();
    }
}
