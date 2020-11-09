package com.imyc.SBAP.Base.CRUDContracts;

import com.imyc.SBAP.Exception.web.WebPageNotFoundException;
import com.imyc.SBAP.Exception.web.WebUpdateException;

public interface UpdateContract<VO, DTO> {
	VO updateResponse(int id) throws WebPageNotFoundException;
	
	boolean updateRequest(DTO dto) throws WebUpdateException;
}
