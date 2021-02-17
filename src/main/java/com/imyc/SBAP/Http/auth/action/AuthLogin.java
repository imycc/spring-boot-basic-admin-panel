package com.imyc.SBAP.Http.auth.action;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imyc.SBAP.Http.auth.dto.LoginDTO;

@Controller
public class AuthLogin {
	
	@GetMapping("/login")
	public String render(Model model, @RequestParam Optional<String> error) {
		
		model.addAttribute("login", new LoginDTO());
		return "admin-panel/auth/login";
	}
	
}
