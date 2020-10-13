package com.imyc.SBAP.Http.role.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleIndex {

	@GetMapping("/role")
	public String role() {
		return "admin-panel/role/index";
	}
}
