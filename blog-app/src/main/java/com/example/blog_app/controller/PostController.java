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

import com.example.blog_app.Service.PostService;
import com.example.blog_app.Service.UserService;
import com.example.blog_app.model.Post;
import com.example.blog_app.model.User;

import jakarta.validation.Valid;

/* This will handle CRUD of Post */
@Controller
public class PostController {

	private final PostService postService;
	private final UserService userService;

	@Autowired
	public PostController(PostService postService, UserService userService) {
		this.postService = postService;
		this.userService = userService;
	}

	@RequestMapping(value = "/newPost", method = RequestMethod.GET)
	public String newPost(Principal principal, Model model) {

		Optional<User> user = userService.findByUsername(principal.getName());

		if(user.isPresent()) {
			Post post = new Post();
			post.setUser(user.get());

			model.addAttribute("post", post);

			return "/postForm";
		}
		else {
			return "/error";
		}

	}
	@RequestMapping(value = "/newPost", method = RequestMethod.POST)
	public String createNewPost(@Valid Post post, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {
			return "/postForm";
		} else {
			postService.save(post);
			return "redirect:blog/" + post.getUser().getUsername();
		}
	}

	@RequestMapping(value = "/editPost/{id}",method = RequestMethod.GET)
	public String editPostWithId(@PathVariable Long id,
			Principal principal,
			Model model) {

		Optional<Post> optionalPost = postService.findForId(id);

		if (optionalPost.isPresent()) {
			Post post = optionalPost.get();

			if (isPrincipalOwnerOfPost(principal, post)) {
				model.addAttribute("post", post);
				return "/postForm";
			} else {
				return "/403";
			}

		} else {
			return "/error";
		}
	}
	private boolean isPrincipalOwnerOfPost(Principal principal, Post post) {
        return principal != null && principal.getName().equals(post.getUser().getUsername());
    }
	
	@RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    public String deletePostWithId(@PathVariable Long id,
                                   Principal principal) {

        Optional<Post> optionalPost = postService.findForId(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            if (isPrincipalOwnerOfPost(principal, post)) {
                postService.delete(post);
                return "redirect:/home";
            } else {
                return "/403";
            }

        } else {
            return "/error";
        }
    }


}

