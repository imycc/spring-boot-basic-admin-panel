package com.imyc.SBAP.Http.user.action;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.imyc.SBAP.Http.user.dto.UserCreateDTO;
import com.imyc.SBAP.Http.user.services.requester.contracts.UserCreateRequester;
import com.imyc.SBAP.Http.user.viewobject.UserCreateVO;

@Controller
public class UserCreate {

	private UserCreateRequester userCreateRequester;
	
	@Autowired
	public UserCreate(@Qualifier("UserDatatableProvider") UserCreateRequester userCreateRequester) {
		this.userCreateRequester = userCreateRequester;
	}

	@GetMapping("/user/create")
	public ModelAndView render() {
		
		UserCreateVO userCreateVO = new UserCreateVO();
		userCreateVO = userCreateRequester.createResponse();
		
		return new ModelAndView("admin-panel/user/create", "userCreateVO" , userCreateVO);
	}
	
	@PostMapping("/user/create")
	public String handle(@Valid @RequestBody UserCreateDTO userCreateDTO) {
		
		userCreateRequester.createRequest(userCreateDTO);
		
		return "redirect:/user?create=success";
	}

}
