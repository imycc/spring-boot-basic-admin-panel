package com.imyc.SBAP.Http.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.imyc.SBAP.Exception.web.WebPageNotFoundException;
import com.imyc.SBAP.Http.user.services.requester.contracts.UserReadRequester;
import com.imyc.SBAP.Http.user.viewobject.UserReadVO;

@Controller
public class UserRead {

	private UserReadRequester userReadContract;
	
	@Autowired
	public UserRead (@Qualifier("UserDatatableProvider") UserReadRequester userReadContract) {
		this.userReadContract = userReadContract;
	}
	
	@GetMapping("/user/read/{id}")
	public ModelAndView render(@PathVariable(value="id") int id) throws WebPageNotFoundException {
		
		UserReadVO userReadVO = userReadContract.readResponse(id);
		
		return new ModelAndView("admin-panel/user/read", "userReadVO", userReadVO);
	}
	
}
