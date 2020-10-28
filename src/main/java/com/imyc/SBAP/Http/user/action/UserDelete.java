package com.imyc.SBAP.Http.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.imyc.SBAP.Http.user.service.UserDatatableProvider;
import com.imyc.SBAP.Exception.web.WebDeleteDataException;

@Controller
public class UserDelete {

	private UserDatatableProvider userDatatableProvider;
	
	@Autowired
	public UserDelete(UserDatatableProvider userDatatableProvider) {
		this.userDatatableProvider = userDatatableProvider;
	}
	
	@PostMapping("/user/delete/{id}")
	public String handle(@PathVariable(value="id") int id) throws WebDeleteDataException {
		
		userDatatableProvider.deleteUser(id);
		
		return "redirect:/user?delete=success";
	}
	
}
