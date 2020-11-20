package com.imyc.SBAP.Http.user.action;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.imyc.SBAP.Exception.web.WebCreateDataException;
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
		
		UserCreateVO userCreateVO = userCreateRequester.createResponse();

		UserCreateDTO userCreateDTO = new UserCreateDTO();
		
		ModelAndView modelAndView = new ModelAndView("admin-panel/user/create");
		modelAndView.addObject("userCreateVO", userCreateVO);
		modelAndView.addObject("userCreateDTO", userCreateDTO);
		
		return modelAndView;
	}
	
	@PostMapping("/user/create")
	public String handle(@Valid @ModelAttribute UserCreateDTO userCreateDTO) throws WebCreateDataException {
		
		userCreateRequester.createRequest(userCreateDTO);
		
		return "redirect:/user?create=success";
	}

}
