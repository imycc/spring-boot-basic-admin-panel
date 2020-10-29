package com.imyc.SBAP.Http.user.services.requester.contracts;

import com.imyc.SBAP.Exception.web.WebDeleteDataException;

public interface UserDeleteRequester {

	boolean deleteUser(int id) throws WebDeleteDataException;
	
}
