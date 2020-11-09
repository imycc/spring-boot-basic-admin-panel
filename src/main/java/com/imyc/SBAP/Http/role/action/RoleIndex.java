package com.imyc.SBAP.Http.role.action;

import com.imyc.SBAP.Http.role.service.requester.contracts.RoleIndexRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleIndex {

	@GetMapping("/role")
	public String render() {
		return "admin-panel/role/index";
	}
}
