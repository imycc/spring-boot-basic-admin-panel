package com.imyc.SBAP.Base.dao.CRUDContracts;

import com.imyc.SBAP.Exception.web.WebPageNotFoundException;

public interface ReadContract<VO> {
	
	VO readResponse(int id) throws WebPageNotFoundException;
	
}
