package com.imyc.SBAP.Base.dao.CRUDContracts;

import com.imyc.SBAP.Exception.web.WebPageNotFoundException;
import com.imyc.SBAP.Exception.web.WebUpdateDataException;

public interface UpdateContract<VO, DTO> {
	VO updateResponse(int id) throws WebPageNotFoundException;
	
	boolean updateRequest(DTO dto, int id) throws WebUpdateDataException;
}
