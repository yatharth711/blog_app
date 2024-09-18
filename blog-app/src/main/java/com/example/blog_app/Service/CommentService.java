package com.example.blog_app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog_app.model.Comment;
import com.example.blog_app.repository.CommentRepository;

public interface CommentService {

    Comment save(Comment comment);
}
