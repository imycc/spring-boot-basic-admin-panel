package com.imyc.SBAP.Base.dao.CRUDContracts;

import com.imyc.SBAP.Exception.web.WebDeleteDataException;

public interface DeleteContract {
	
	boolean deleteRequest() throws WebDeleteDataException;
	
}
