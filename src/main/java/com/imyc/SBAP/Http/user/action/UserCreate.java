package com.imyc.SBAP.Http.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.imyc.SBAP.Http.user.services.requester.contracts.UserCreateRequester;
import com.imyc.SBAP.Http.user.viewobject.UserCreateVO;

@Controller
public class UserCreate {

	private UserCreateRequester userCreateContract;
	
	@Autowired
	public UserCreate(UserCreateRequester userCreateContract) {
		this.userCreateContract = userCreateContract;
	}

	@GetMapping("/user/create")
	public ModelAndView handle() {
		
		UserCreateVO userCreateVO = new UserCreateVO();
		userCreateVO = userCreateContract.loadRoleListForUserCreate();
		
		return new ModelAndView("admin-panel/user/create", "userCreateVO" , userCreateVO);
	}

}
