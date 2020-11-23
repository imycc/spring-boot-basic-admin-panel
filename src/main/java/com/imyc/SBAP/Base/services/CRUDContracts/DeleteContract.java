package com.imyc.SBAP.Base.services.CRUDContracts;

import com.imyc.SBAP.Exception.web.WebDeleteDataException;

public interface DeleteContract {
	
	boolean deleteRequest(int id) throws WebDeleteDataException;
	
}
