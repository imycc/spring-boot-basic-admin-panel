package com.imyc.SBAP.Http.user.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.imyc.SBAP.Http.role.viewobject.RoleVO;

@Controller
public class UserCreate {

	@GetMapping("/user/create")
	public ModelAndView handle() {
		
		List<RoleVO> roleVOList = new ArrayList<RoleVO>();
		RoleVO roleVO1 = new RoleVO();
		roleVO1.setId(1);
		roleVO1.setName("admin");
		
		RoleVO roleVO2 = new RoleVO();
		roleVO2.setId(2);
		roleVO2.setName("user");
		
		roleVOList.add(roleVO1);
		roleVOList.add(roleVO2);
		
		return new ModelAndView("admin-panel/user/create", "roleVOList" , roleVOList);
	}

}
