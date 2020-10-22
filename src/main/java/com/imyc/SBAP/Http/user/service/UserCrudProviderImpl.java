package com.imyc.SBAP.Http.user.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.imyc.SBAP.Http.user.dao.UserCrudDAO;
import com.imyc.SBAP.Http.user.persistent.object.UserDatatablePO;

public class UserCrudProviderImpl implements UserCrudProvider{

	private UserCrudDAO userCrudDAO;

	@Autowired
	public UserCrudProviderImpl(UserCrudDAO userCrudDAO) {
		this.userCrudDAO = userCrudDAO;
	}
	
	@Override
	public UserDatatablePO loadAllUserForDatatable() {
		
		
		
		return null;
	}

}
