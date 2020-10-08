package com.imyc.SBAP.Http.auth.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.imyc.SBAP.Http.auth.model.Login;
import com.imyc.SBAP.Http.auth.service.AuthServiceImpl;

@Controller
public class UserLogin {
	
	@Autowired
	private AuthServiceImpl authService;

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("login", new Login());
		return "admin-panel/auth/login";
	}
	
	@PostMapping("/login")
	public String loginSubmit(@ModelAttribute Login login, Model model) {
		model.addAttribute("login", login);
		authService = new AuthServiceImpl();
		boolean isLogin = authService.login();
		
		if (isLogin == true) {
			return "redirect:/dashboard";
		}else {
			return "redirect:/login";
		}
	}
	
}
