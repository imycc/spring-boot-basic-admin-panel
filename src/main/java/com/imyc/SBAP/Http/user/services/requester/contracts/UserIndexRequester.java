package com.imyc.SBAP.Http.user.services.requester.contracts;

import java.util.HashMap;

import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;

public interface UserIndexRequester {

	UserDatatableVO loadAllUserForDatatable(HashMap<String, Object> serverSideConfig);
	
}
