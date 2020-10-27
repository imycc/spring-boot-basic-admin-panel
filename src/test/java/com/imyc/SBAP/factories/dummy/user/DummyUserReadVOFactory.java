package com.imyc.SBAP.factories.dummy.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imyc.SBAP.Http.user.viewobject.UserReadVO;
import com.imyc.SBAP.factories.dummy.DummyFactory;

public class DummyUserReadVOFactory implements DummyFactory<UserReadVO>{

	private UserReadVO dummyUserReadVO;
	private Date date;
	private List<String> roleList;

	public DummyUserReadVOFactory() {
		String source = "2019-12-31";
		String pattern = "yyyy-mm-dd";
		try {
		    date = new SimpleDateFormat(pattern).parse(source);
		} catch (ParseException e) {
		    System.out.printf("Parse date string [%1$s] with pattern [%2$s] error.%n", source, pattern);
		}
		
		roleList = new ArrayList<String>();
		roleList.add("Admin");
		roleList.add("User");
		
		dummyUserReadVO = new UserReadVO();
	}

	@Override
	public UserReadVO make() {
		
		dummyUserReadVO
			.setId(1)
			.setName("Admin")
			.setEmail("test@test.com")
			.setUsername("admin")
			.setCreatedAt(date)
			.setUpdatedAt(date)
			.setDisabled(false)
			.setRoles(roleList.toArray(new String[0]));
		
		return dummyUserReadVO;
	}
	
}
