package com.imyc.SBAP.Http.user.action.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GetUserDatatable {

	@GetMapping("api/datatable/user")
	public Map<String,Object> getUsersForDatatable() {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("key", "value");
		
		return map;
	}
	
}
