package com.imyc.SBAP.Http.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.imyc.SBAP.Exception.web.WebDeleteDataException;
import com.imyc.SBAP.Http.user.services.requester.contracts.UserDeleteRequester;

@Controller
public class UserDelete {

	private UserDeleteRequester userDeleteContract;
	
	@Autowired
	public UserDelete(UserDeleteRequester userDatatableProvider) {
		this.userDeleteContract = userDatatableProvider;
	}
	
	@PostMapping("/user/delete/{id}")
	public String handle(@PathVariable(value="id") int id) throws WebDeleteDataException {
		
		userDeleteContract.deleteUser(id);
		
		return "redirect:/user?delete=success";
	}
	
}
