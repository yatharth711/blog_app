package com.example.blog_app.Service;

import java.util.Optional;

import com.example.blog_app.model.User;
import com.example.blog_app.repository.UserRepository;

public interface UserService {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User save(User user);
}