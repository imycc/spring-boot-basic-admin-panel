package com.imyc.SBAP.Http.user.service;

import java.util.HashMap;

import com.imyc.SBAP.Exception.web.UserNotFoundException;
import com.imyc.SBAP.Exception.web.WebPageNotFoundException;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import com.imyc.SBAP.Http.user.viewobject.UserReadVO;

public interface UserDatatableProvider {

	UserDatatableVO loadAllUserForDatatable(HashMap<String, Object> serverSideConfig);
	
	UserReadVO loadUserForUserRead(int id) throws WebPageNotFoundException;
	
}
