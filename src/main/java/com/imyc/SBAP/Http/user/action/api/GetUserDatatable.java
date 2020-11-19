package com.imyc.SBAP.Http.user.action.api;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imyc.SBAP.Http.user.services.requester.contracts.UserIndexRequester;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;

@RestController
public class GetUserDatatable {
	
	private UserIndexRequester userIndexContract;
	private DatatableServerSideConfig datatableServerSideConfig;
	
	@Autowired
	public GetUserDatatable (@Qualifier("UserDatatableProvider") UserIndexRequester userIndexContract) {
		this.userIndexContract = userIndexContract;
	}

	@GetMapping("api/datatable/user")
	public ResponseEntity<UserDatatableVO> handle(
			@RequestParam int draw, @RequestParam int start, @RequestParam int length, 
			@RequestParam(name="search[value]", required = false) String keyword) {

		datatableServerSideConfig = new DatatableServerSideConfig();
		datatableServerSideConfig
				.setDraw(draw)
				.setStart(start)
				.setLength(length)
				.setKeyword(keyword.trim());
		
		UserDatatableVO result = userIndexContract.indexResponse(datatableServerSideConfig);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
