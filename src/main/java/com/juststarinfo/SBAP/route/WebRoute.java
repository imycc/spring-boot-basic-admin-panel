package com.juststarinfo.SBAP.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebRoute {

	@GetMapping("/")
    public String home() { return "admin-panel/index"; }
	
	@GetMapping("/login.html")
	public String login() { return "admin-panel/login"; }
	
	@GetMapping("/register.html")
	public String register() { return "admin-panel/register"; }
	
	@GetMapping("/index.html")
    public String index() { return "admin-panel/index"; }

	@GetMapping("/pages/ui-features/buttons.html")
	public String buttons() { return "admin-panel/ui-features/buttons"; }
	
	@GetMapping("/error-403.html")
	public String error403() { return "admin-panel/error/error-403"; }
	
	@GetMapping("/error-404.html")
	public String error404() { return "admin-panel/error/error-404"; }
	
	@GetMapping("/error-500.html")
	public String error500() { return "admin-panel/error/error-500"; }
	
	
}
