package com.imyc.SBAP.Http.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.imyc.SBAP.Exception.web.WebDeleteDataException;
import com.imyc.SBAP.Http.user.services.requester.contracts.UserDeleteRequester;

@Controller
public class UserDelete {

	private UserDeleteRequester userDeleteContract;
	
	@Autowired
	public UserDelete(@Qualifier("UserDatatableProvider") UserDeleteRequester userDeleteContract) {
		this.userDeleteContract = userDeleteContract;
	}
	
	@PostMapping("/user/delete/{id}")
	public String handle(@PathVariable(value="id") int id) throws WebDeleteDataException {
		
		userDeleteContract.deleteRequest(id);
		
		return "redirect:/user?delete=success";
	}
	
}
