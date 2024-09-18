package com.example.blog_app.repository;

import java.util.Optional;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog_app.model.Post;
import com.example.blog_app.model.User;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByUserOrderByCreateDateDesc(User user, Pageable pageable);

    Page<Post> findAllByOrderByCreateDateDesc(Pageable pageable);

    Optional<Post> findById(Long id);
}
