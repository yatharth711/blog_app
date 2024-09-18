package com.example.blog_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.blog_app.Service.PostService;
import com.example.blog_app.model.Post;
import com.example.blog_app.util.Pager;

@Controller
public class HomeController {

	
	private final PostService postService;
	
	
	@Autowired
	public HomeController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/home")
	public String home(@RequestParam(defaultValue="0") int page, Model model ) {
		
		Page<Post> posts = postService.findAllOrderedByDatePageable(page);
		
		
		Pager pager = new Pager(posts);
		
		return "/home";
	}
}
