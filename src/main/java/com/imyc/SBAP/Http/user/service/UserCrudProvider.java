package com.imyc.SBAP.Http.user.service;

import com.imyc.SBAP.Http.user.persistent.object.UserDatatablePO;

public interface UserCrudProvider {

	UserDatatablePO loadAllUserForDatatable();
	
}
