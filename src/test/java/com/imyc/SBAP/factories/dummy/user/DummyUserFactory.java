package com.imyc.SBAP.factories.dummy.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.imyc.SBAP.Http.role.dao.Role;
import com.imyc.SBAP.Http.user.dao.User;
import com.imyc.SBAP.factories.dummy.DummyFactory;
import com.imyc.SBAP.factories.dummy.role.DummyRoleFactory;

public class DummyUserFactory implements DummyFactory<User>{

	private User user;
	private Role dummyRole;
	private Date date;
	private Set<Role> dummyRoleSet;
	
	public DummyUserFactory(String role) {
		this.date = currentDate();
		if (role.equals("ADMIN")) {
			dummyRole = new DummyRoleFactory(1, "ADMIN").make();
		}else{
			dummyRole = new DummyRoleFactory(2, "USER").make();
		}

		dummyRoleSet = new HashSet<>();
		dummyRoleSet.add(dummyRole);
	}
	
	@Override
	public User make() {
		user = new User();
		user
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
		
		return user;
	}

}
