package com.imyc.SBAP.factories.dummy.user;

import java.util.ArrayList;
import java.util.List;

import com.imyc.SBAP.Http.user.dto.UserCreateDTO;
import com.imyc.SBAP.factories.dummy.DummyFactory;

public class DummyUserCreateDTOFactory implements DummyFactory<UserCreateDTO>{

	private UserCreateDTO userCreateDTO;

	@Override
	public UserCreateDTO make() {
		List<Integer> roles = new ArrayList<>();
		roles.add(1);
		
		userCreateDTO = new UserCreateDTO();
		userCreateDTO
			.setName("Test Name")
			.setUsername("TestUsername")
			.setEmail("test@test.com")
			.setPassword("testpassword")
			.setRoles(roles);

		return userCreateDTO;
	}
	
}
