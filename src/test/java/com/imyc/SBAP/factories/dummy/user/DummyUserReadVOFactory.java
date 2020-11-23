package com.imyc.SBAP.factories.dummy.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imyc.SBAP.Http.user.viewobject.UserReadVO;
import com.imyc.SBAP.factories.dummy.DummyFactory;

public class DummyUserReadVOFactory implements DummyFactory<UserReadVO>{

	private UserReadVO userReadVO;
	private Date date;
	private List<String> roleList;

	public DummyUserReadVOFactory() {
		this.date = currentDate();
		
		roleList = new ArrayList<>();
		roleList.add("Admin");
		roleList.add("User");
		
		userReadVO = new UserReadVO();
	}

	@Override
	public UserReadVO make() {
		
		userReadVO
			.setId(1)
			.setName("Admin")
			.setEmail("test@test.com")
			.setUsername("admin")
			.setCreatedAt(date)
			.setUpdatedAt(date)
			.setDisabled(false)
			.setRoles(roleList.toArray(new String[0]));
		
		return userReadVO;
	}
	
}
