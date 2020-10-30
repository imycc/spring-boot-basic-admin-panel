package com.imyc.SBAP.Base.CRUDContracts;

public interface UpdateContract<VO, DTO> {
	VO updateResponse();
	
	boolean updateRequest(DTO dto);
}
