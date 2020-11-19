package com.imyc.SBAP.Http.role.action.api;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import com.imyc.SBAP.Http.role.service.requester.contracts.RoleIndexRequester;
import com.imyc.SBAP.Http.role.viewobject.RoleDatatableVO;
import com.imyc.SBAP.Http.user.services.requester.contracts.UserIndexRequester;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class GetRoleDatatable {

	private RoleIndexRequester roleIndexContract;
	private DatatableServerSideConfig datatableServerSideConfig;

	@Autowired
	public GetRoleDatatable(@Qualifier("RoleDatatableProvider") RoleIndexRequester roleIndexContract) {
		this.roleIndexContract = roleIndexContract;
	}

	@GetMapping("api/datatable/role")
	public ResponseEntity<RoleDatatableVO> handle(
			@RequestParam int draw, @RequestParam int start, @RequestParam int length, 
			@RequestParam(name="search[value]", required = false) String keyword) {

		datatableServerSideConfig = new DatatableServerSideConfig();
		datatableServerSideConfig
				.setDraw(draw)
				.setStart(start)
				.setLength(length)
				.setKeyword(keyword.trim());

		RoleDatatableVO roleDatatableVO = roleIndexContract.indexResponse(datatableServerSideConfig);
		
		return new ResponseEntity<>(roleDatatableVO, HttpStatus.OK);
	}
	
}
