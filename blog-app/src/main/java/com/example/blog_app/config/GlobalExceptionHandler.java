package com.example.blog_app.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView exception(final Throwable throwable) {
		logger.error("Exception during exeution of spring security", throwable);
		ModelAndView modelAndView = new ModelAndView("/error");
		
		String err = (throwable != null ? throwable.toString() : "Error not found" );
		modelAndView.addObject(err);
		
		return modelAndView;
	}
	
	
}
