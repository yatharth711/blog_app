package com.example.blog_app.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.blog_app.Service.CommentService;
import com.example.blog_app.Service.PostService;
import com.example.blog_app.Service.UserService;
import com.example.blog_app.model.Comment;
import com.example.blog_app.model.Post;
import com.example.blog_app.model.User;

import jakarta.validation.Valid;

@Controller
public class CommentController {

	private final PostService postService;
	private final UserService userService;
	private final CommentService commentService;

	@Autowired
	public CommentController(PostService postService, UserService userService, CommentService commentService) {
		super();
		this.postService = postService;
		this.userService = userService;
		this.commentService = commentService;
	}

	@RequestMapping(value ="/createComment", method = RequestMethod.POST)
	public String createNewPost(@Valid Comment comment, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {
			return "/commentForm";
		} else {
			commentService.save(comment);

			return "redirect:/post/" + comment.getPost().getId();
		}
	}

	@RequestMapping(value = "/commentPost/{id}", method = RequestMethod.GET)
	public String commentPostWithId(@PathVariable Long id, Principal principal, Model model) {

		Optional<Post> post = postService.findForId(id);

		if(post.isPresent()) {
			Optional<User> user = userService.findByUsername(principal.getName());

			if(user.isPresent()) {
				Comment comment = new Comment();

				comment.setUser(user.get());
				comment.setPost(post.get());

				model.addAttribute("comment",comment);

				return "/commentForm";
			} else {
				return "/error";
			}

		} else {
			return "/error";
		}

	}





}
