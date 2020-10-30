package com.imyc.SBAP.Base.CRUDContracts;

public interface CreateContract<VO, DTO> {
	
	VO createResponse();
	
	boolean createRequest(DTO dto);
}
