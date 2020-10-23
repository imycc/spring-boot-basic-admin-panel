package com.imyc.SBAP.Http.user.action.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imyc.SBAP.Http.user.service.UserDatatableProvider;


@RestController
public class GetUserDatatable {
	
	private UserDatatableProvider userDatatableProvider;
	
	@Autowired
	public GetUserDatatable (UserDatatableProvider userDatatableProvider) {
		this.userDatatableProvider = userDatatableProvider;
	}

	@GetMapping("api/datatable/user")
	public List<Map<String,Object>> getUsersForDatatable() {
		
		List<Map<String,Object>> result = userDatatableProvider.loadAllUserForDatatable();
		
		return result;
	}
	
}
