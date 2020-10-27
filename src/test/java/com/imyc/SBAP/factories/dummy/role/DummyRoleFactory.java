package com.imyc.SBAP.factories.dummy.role;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.imyc.SBAP.Http.role.model.Roles;
import com.imyc.SBAP.factories.dummy.DummyFactory;

public class DummyRoleFactory implements DummyFactory<Roles>{

	private Roles dummyRole;
	private Boolean isAdmin;
	private String roleName;
	private Date date;
	
	public DummyRoleFactory(String roleName) {
		this.roleName = roleName;  
		if (roleName.equals("ADMIN")) {
			this.isAdmin = true;
		}else {
			this.isAdmin = false;
		}
		
		String source = "2019-12-31";
		String pattern = "yyyy-mm-dd";
		try {
		    this.date = new SimpleDateFormat(pattern).parse(source);
		} catch (ParseException e) {
		    System.out.printf("Parse date string [%1$s] with pattern [%2$s] error.%n", source, pattern);
		}
	}
	
	@Override
	public Roles make() {
		dummyRole = new Roles();
		dummyRole
			.setAdmin(isAdmin)
			.setId(1)
			.setName(roleName)
			.setCreatedAt(date)
			.setUpdatedAt(date);
		return dummyRole;
	}

}
