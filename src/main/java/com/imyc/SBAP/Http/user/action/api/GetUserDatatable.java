package com.imyc.SBAP.Http.user.action.api;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imyc.SBAP.Http.user.service.UserDatatableProvider;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;


@RestController
public class GetUserDatatable {
	
	private UserDatatableProvider userDatatableProvider;
	
	@Autowired
	public GetUserDatatable (UserDatatableProvider userDatatableProvider) {
		this.userDatatableProvider = userDatatableProvider;
	}

	@GetMapping("api/datatable/user")
	public ResponseEntity<UserDatatableVO> getUsersForDatatable(
			@RequestParam int draw, @RequestParam int start, @RequestParam int length) {
		
		HashMap<String, Object> serverSideConfig = new HashMap<>();
		
		serverSideConfig.put("draw", draw);
		serverSideConfig.put("start", start);
		serverSideConfig.put("length", length);
		
		UserDatatableVO result = userDatatableProvider.loadAllUserForDatatable(serverSideConfig);
		
		return new ResponseEntity<UserDatatableVO>(result, HttpStatus.OK);
	}
	
}
