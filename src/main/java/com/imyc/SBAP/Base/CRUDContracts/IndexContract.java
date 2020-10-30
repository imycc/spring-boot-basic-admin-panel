package com.imyc.SBAP.Base.CRUDContracts;

import java.util.HashMap;

public interface IndexContract<VO> {
	VO indexResponse(HashMap<String, Object> serverSideConfig);
}
