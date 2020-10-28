package com.imyc.SBAP.Http.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.imyc.SBAP.Exception.web.WebPageNotFoundException;
import com.imyc.SBAP.Http.user.service.UserDatatableProvider;
import com.imyc.SBAP.Http.user.viewobject.UserReadVO;

@Controller
public class UserRead {

	private UserDatatableProvider userDatatableProvider;
	
	@Autowired
	public UserRead (UserDatatableProvider userDatatableProvider) {
		this.userDatatableProvider = userDatatableProvider;
	}
	
	@GetMapping("/user/read/{id}")
	public ModelAndView handle(@PathVariable(value="id") int id) throws WebPageNotFoundException {
		
		UserReadVO userReadVO = userDatatableProvider.loadUserForUserRead(id);
		
		if (userReadVO == null) {
			throw new WebPageNotFoundException();
		}
		
		return new ModelAndView("admin-panel/user/read", "userReadVO", userReadVO);
	}
	
}
