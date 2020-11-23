package com.imyc.SBAP.Base.services.CRUDContracts;

import com.imyc.SBAP.Exception.web.WebCreateDataException;

public interface CreateContract<VO, DTO> {
	
	VO createResponse();
	
	boolean createRequest(DTO dto) throws WebCreateDataException;
}
