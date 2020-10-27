package com.imyc.SBAP.factories.dummy.user;

import com.imyc.SBAP.Http.user.viewobject.datatable.UserRow;
import com.imyc.SBAP.factories.dummy.DummyFactory;

public class DummyUserRowFactory implements DummyFactory<UserRow>{

	private UserRow dummyUserRow;
	
	public DummyUserRowFactory() {
		dummyUserRow = new UserRow();
	}

	@Override
	public UserRow make() {
		dummyUserRow
			.setId(1)
			.setName("test")
			.setEmail("test@test.com")
			.setDisabled(false);
		
		return dummyUserRow;
	}
}
