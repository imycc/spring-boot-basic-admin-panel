package com.imyc.SBAP.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.imyc.SBAP.Exception.web.WebDeleteDataException;
import com.imyc.SBAP.Exception.web.WebPageNotFoundException;

@ControllerAdvice
public class WebExceptionHandler {

	@ExceptionHandler(WebPageNotFoundException.class)
	public String handleNotFoundException(final WebPageNotFoundException e) {
		return "admin-panel/error/404";
	}
	
	@ExceptionHandler(WebDeleteDataException.class)
	public String handleDeleteFailureException(final WebDeleteDataException e) {
		return "redirct:/user?delete=failure";
	}
	
}
