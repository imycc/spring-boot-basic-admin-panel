package com.imyc.SBAP.Http.user.services.requester.contracts;

import com.imyc.SBAP.Exception.web.WebPageNotFoundException;
import com.imyc.SBAP.Http.user.viewobject.UserReadVO;

public interface UserReadRequester {

	UserReadVO loadUserForUserRead(int id) throws WebPageNotFoundException;
	
}
