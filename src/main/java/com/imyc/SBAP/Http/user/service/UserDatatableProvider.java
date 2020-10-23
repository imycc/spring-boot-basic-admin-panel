package com.imyc.SBAP.Http.user.service;

import java.util.HashMap;

import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;

public interface UserDatatableProvider {

	UserDatatableVO loadAllUserForDatatable(HashMap<String, Object> serverSideConfig);
	
}
