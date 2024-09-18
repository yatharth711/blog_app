package com.example.blog_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.blog_app.Service.UserService;
import com.example.blog_app.model.User;

import jakarta.validation.Valid;

@Controller
public class RegistrationController {

	
	private final UserService userService;

	@Autowired
	public RegistrationController(UserService userService) {
		
		this.userService = userService;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		
		model.addAttribute("user", new User());
		return "/registration";
		
	}
	
	@RequestMapping(value = "/registration" , method = RequestMethod.POST)
	public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
		
		if(userService.findByEmail(user.getEmail()).isPresent()) {
			bindingResult.rejectValue("email", "error.user","User with this email exists");
		}
		
		if(userService.findByUsername(user.getUsername()).isPresent()) {
			bindingResult.rejectValue("username", "error.user", "User with this username exists");
			
		}
		
		if(!bindingResult.hasErrors()) {
			userService.save(user);
			
			model.addAttribute("sucesss","User registered sucessfully");
			model.addAttribute("user", new User());
			
		}
		
		return "/registration";
	}
	
	
	
	
	
}
