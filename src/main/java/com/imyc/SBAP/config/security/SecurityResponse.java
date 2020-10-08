package com.imyc.SBAP.config.security;

import org.springframework.http.HttpStatus;

public class SecurityResponse {

	private Integer status;
	private String message;

	public SecurityResponse() {
		this.status = HttpStatus.OK.value();
		this.message = "success";
	}

	public SecurityResponse(Integer status, String message) {
		this.status = status;
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
