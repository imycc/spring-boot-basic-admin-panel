package com.imyc.SBAP.Http.user.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserRead {

	@GetMapping("/user/read/{id}")
	public String userRead(@PathVariable(value="id") int id, Model model) {
		model.addAttribute("id", id);
		return "admin-panel/user/read";
	}
}
