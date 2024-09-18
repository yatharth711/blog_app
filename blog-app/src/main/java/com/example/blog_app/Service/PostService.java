package com.example.blog_app.Service;

import org.springframework.stereotype.Service;

import com.example.blog_app.model.Post;
import com.example.blog_app.model.User;
import com.example.blog_app.repository.PostRepository;
import com.example.blog_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

public interface PostService{
	
	Optional<Post> findForId(Long Id);
	
	Post save(Post post);
	
	Page<Post> findByUserOrderByDatePageable(User user, int page);
	
	
	Page<Post> findAllOrderedByDatePageable(int page);
	
	void delete(Post post);
	
}