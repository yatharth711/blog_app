package com.example.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog_app.model.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {

	
}
