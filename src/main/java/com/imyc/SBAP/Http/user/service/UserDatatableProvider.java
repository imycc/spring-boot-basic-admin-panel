package com.imyc.SBAP.Http.user.service;

import java.util.List;
import java.util.Map;

public interface UserDatatableProvider {

	List<Map<String, Object>> loadAllUserForDatatable();
	
}
