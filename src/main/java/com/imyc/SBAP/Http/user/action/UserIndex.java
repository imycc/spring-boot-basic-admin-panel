package com.imyc.SBAP.Http.user.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserIndex {

	@GetMapping("/user")
	public String render() {
		return "admin-panel/user/index";
	}
	
}
