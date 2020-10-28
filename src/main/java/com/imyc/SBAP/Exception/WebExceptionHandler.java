package com.imyc.SBAP.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.imyc.SBAP.Exception.web.WebPageNotFoundException;

@ControllerAdvice
public class WebExceptionHandler {

	@ExceptionHandler(WebPageNotFoundException.class)
	public String handleNotFoundException(final WebPageNotFoundException e) {
		return "admin-panel/error/404";
	}
	
}
