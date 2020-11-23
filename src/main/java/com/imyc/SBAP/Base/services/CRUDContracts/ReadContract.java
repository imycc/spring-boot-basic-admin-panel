package com.imyc.SBAP.Base.services.CRUDContracts;

import com.imyc.SBAP.Exception.web.WebPageNotFoundException;

public interface ReadContract<VO> {
	
	VO readResponse(int id) throws WebPageNotFoundException;
	
}
