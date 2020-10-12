package com.imyc.SBAP.Http.auth.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.imyc.SBAP.Http.auth.model.Login;

@Controller
public class AuthLogin {
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("login", new Login());
		return "admin-panel/auth/login";
	}
	
}
