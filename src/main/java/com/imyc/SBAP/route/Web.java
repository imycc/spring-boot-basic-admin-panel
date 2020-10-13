package com.imyc.SBAP.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Web {

	@GetMapping("/dashboard")
	public String home() {
		return "admin-panel/index";
	}

	@GetMapping("/register")
	public String register() {
		return "admin-panel/auth/register";
	}

	@GetMapping("/error-403")
	public String error403() {
		return "admin-panel/error/error-403";
	}

	@GetMapping("/error-404")
	public String error404() {
		return "admin-panel/error/error-404";
	}

	@GetMapping("/error-500")
	public String error500() {
		return "admin-panel/error/error-500";
	}

}
