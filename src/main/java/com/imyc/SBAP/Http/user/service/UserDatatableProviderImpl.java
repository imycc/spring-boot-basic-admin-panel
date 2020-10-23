package com.imyc.SBAP.Http.user.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imyc.SBAP.Http.user.dao.UserDatatableDAO;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;

@Service
public class UserDatatableProviderImpl implements UserDatatableProvider{

	private UserDatatableDAO userDatatableDAO;

	@Autowired
	public UserDatatableProviderImpl(UserDatatableDAO userDatatableDAO) {
		this.userDatatableDAO = userDatatableDAO;
	}
	
	@Override
	public UserDatatableVO loadAllUserForDatatable(HashMap<String, Object> serverSideConfig) {
		
		UserDatatableVO userDatatableVO = userDatatableDAO.getUserDatatableVO(serverSideConfig);
		
		return userDatatableVO;
	}

}
