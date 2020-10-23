package com.imyc.SBAP.Http.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.imyc.SBAP.Http.user.dao.UserDatatableDAO;

public class UserDatatableProviderImpl implements UserDatatableProvider{

	private UserDatatableDAO userDatatableDAO;

	@Autowired
	public UserDatatableProviderImpl(UserDatatableDAO userDatatableDAO) {
		this.userDatatableDAO = userDatatableDAO;
	}
	
	@Override
	public List<Map<String, Object>> loadAllUserForDatatable() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		HashMap<String, Object> map = new HashMap<>();
		map.put("key", "value");
		list.add(map);
		
		return list;
	}

}
