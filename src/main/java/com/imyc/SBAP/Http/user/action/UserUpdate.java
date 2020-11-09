package com.imyc.SBAP.Http.user.action;

import com.imyc.SBAP.Exception.web.WebPageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.imyc.SBAP.Http.user.dto.UserUpdateDTO;
import com.imyc.SBAP.Http.user.services.requester.contracts.UserUpdateRequester;
import com.imyc.SBAP.Http.user.viewobject.UserUpdateVO;

@Controller
public class UserUpdate {
	
	private UserUpdateRequester userUpdateContract;
	
	@Autowired
	public UserUpdate (@Qualifier("UserDatatableProvider") UserUpdateRequester userUpdateContract) {
		this.userUpdateContract = userUpdateContract;
	}

	@GetMapping("/user/update/{id}")
	public ModelAndView render(@PathVariable(value="id") int id) throws WebPageNotFoundException {
		
		UserUpdateVO userUpdateVO = userUpdateContract.updateResponse(id);
		
		UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
		
		ModelAndView modelAndView = new ModelAndView("admin-panel/user/update");
		modelAndView.addObject("id", id);
		modelAndView.addObject("userUpdateVO", userUpdateVO);
		modelAndView.addObject("userUpdateDTO", userUpdateDTO);
		
		return modelAndView;
	}
	
//	@PostMapping("/user/update")
//	public String handle(@Valid @ModelAttribute UserUpdateDTO userUpdateDTO) throws WebCreateDataException {
//		
//		userUpdateContract.updateRequest(userCreateDTO);
//		
//		return "redirect:/user?update=success";
//	}
	
}
