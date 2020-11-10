package com.imyc.SBAP.Http.role.action.api;

import com.imyc.SBAP.Http.role.service.requester.contracts.RoleIndexRequester;
import com.imyc.SBAP.Http.role.viewobject.RoleDatatableVO;
import com.imyc.SBAP.Http.user.services.requester.contracts.UserIndexRequester;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class GetRoleDatatable {

	private RoleIndexRequester roleIndexContract;

	@Autowired
	public GetRoleDatatable(RoleIndexRequester roleIndexContract) {
		this.roleIndexContract = roleIndexContract;
	}

	@GetMapping("api/datatable/role")
	public ResponseEntity<RoleDatatableVO> handle(
			@RequestParam int draw, @RequestParam int start, @RequestParam int length, 
			@RequestParam(name="search[value]", required = false) String keyword) {
		
		HashMap<String, Object> serverSideConfig = new HashMap<>();
		
		serverSideConfig.put("draw", draw);
		serverSideConfig.put("start", start);
		serverSideConfig.put("length", length);
		serverSideConfig.put("keyword", keyword.trim());
		
		RoleDatatableVO result = roleIndexContract.indexResponse(serverSideConfig);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
