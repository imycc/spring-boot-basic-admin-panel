package com.imyc.SBAP.factories.dummy.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.imyc.SBAP.Http.role.dao.Roles;
import com.imyc.SBAP.Http.user.dao.Users;
import com.imyc.SBAP.factories.dummy.DummyFactory;
import com.imyc.SBAP.factories.dummy.role.DummyRoleFactory;

public class DummyUserFactory implements DummyFactory<Users>{

	private Users dummyUser;
	private Roles dummyRole;
	private Date date;
	private Set<Roles> dummyRoleSet;
	
	public DummyUserFactory(String role) {
		
		String source = "2019-12-31";
		String pattern = "yyyy-mm-dd";
		try {
		    this.date = new SimpleDateFormat(pattern).parse(source);
		} catch (ParseException e) {
		    System.out.printf("Parse date string [%1$s] with pattern [%2$s] error.%n", source, pattern);
		}
		
		if (role.equals("ADMIN")) {
			dummyRole = new DummyRoleFactory(1, "ADMIN").make();
		}else{
			dummyRole = new DummyRoleFactory(2, "USER").make();
		}

		dummyRoleSet = new HashSet<>();
		dummyRoleSet.add(dummyRole);
	}
	
	@Override
	public Users make() {
		dummyUser = new Users();
		dummyUser
			.setId(1)
			.setName("admin")
			.setEmail("test@test.com")
			.setPassword("admin")
			.setDisabled(false)
			.setAccountExpired(false)
			.setAccountLocked(false)
			.setCredentialsExpired(false)
			.setRoles(dummyRoleSet)
			.setCreatedAt(date)
			.setUpdatedAt(date);
		
		return dummyUser;
	}

}
