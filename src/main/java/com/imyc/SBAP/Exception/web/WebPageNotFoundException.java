package com.imyc.SBAP.Exception.web;

public class WebPageNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public WebPageNotFoundException() {
		super("Not Found - 404");
	}
	
}
