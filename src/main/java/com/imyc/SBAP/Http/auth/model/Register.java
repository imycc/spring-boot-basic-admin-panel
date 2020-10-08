package com.imyc.SBAP.Http.auth.model;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class Register {

	@GetMapping("/register")
	public String register(Model model) {
		return "admin-panel/auth/register";
	}
	
}
