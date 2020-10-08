package com.imyc.SBAP.Http.user.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserIndex {

	@GetMapping("/user")
	public String user(Model model) {
		return "admin-panel/user/index";
	}
	
}
