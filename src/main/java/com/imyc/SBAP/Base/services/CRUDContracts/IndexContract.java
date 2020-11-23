package com.imyc.SBAP.Base.services.CRUDContracts;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;

public interface IndexContract<VO> {
	VO indexResponse(DatatableServerSideConfig datatableServerSideConfig);
}
