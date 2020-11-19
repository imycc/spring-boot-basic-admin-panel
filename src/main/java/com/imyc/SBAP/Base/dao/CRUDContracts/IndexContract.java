package com.imyc.SBAP.Base.dao.CRUDContracts;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;

public interface IndexContract<VO> {
	VO indexResponse(DatatableServerSideConfig datatableServerSideConfig);
}
