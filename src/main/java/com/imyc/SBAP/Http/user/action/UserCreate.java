package com.imyc.SBAP.Http.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.imyc.SBAP.Http.user.services.Requester.UserDatatableProvider;
import com.imyc.SBAP.Http.user.viewobject.UserCreateVO;

@Controller
public class UserCreate {

	private UserDatatableProvider userDatatableProvider;
	
	@Autowired
	public UserCreate(UserDatatableProvider userDatatableProvider) {
		this.userDatatableProvider = userDatatableProvider;
	}

	@GetMapping("/user/create")
	public ModelAndView handle() {
		
		UserCreateVO userCreateVO = new UserCreateVO();
		userCreateVO = userDatatableProvider.loadRoleListForUserCreate();
		
		return new ModelAndView("admin-panel/user/create", "userCreateVO" , userCreateVO);
	}
	
	@PostMapping("/user/create")
	public String create() {
		
		return null;
	}

}
