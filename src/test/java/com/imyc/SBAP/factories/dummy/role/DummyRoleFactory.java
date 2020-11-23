package com.imyc.SBAP.factories.dummy.role;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.imyc.SBAP.Http.privilege.dao.Privilege;
import com.imyc.SBAP.Http.role.dao.Role;
import com.imyc.SBAP.factories.dummy.DummyFactory;
import com.imyc.SBAP.factories.dummy.privilege.DummyPrivilegeFactory;

public class DummyRoleFactory implements DummyFactory<Role>{

	private Role role;
	private Boolean isAdmin;
	private int id;
	private String roleName;
	private Date date;
	private Set<Privilege> dummyPrivilegeSet;
	
	public DummyRoleFactory(int id, String roleName) {
		this.id = id;
		this.roleName = roleName;

		if (roleName.equals("ADMIN")) {
			this.isAdmin = true;
		}else {
			this.isAdmin = false;
		}

		this.date = currentDate();

		Privilege dummyPrivilege = new DummyPrivilegeFactory(1, "User_TEST").make();
		this.dummyPrivilegeSet = new HashSet<>();
		dummyPrivilegeSet.add(dummyPrivilege);
	}
	
	@Override
	public Role make() {
		role = new Role();
		role
				.setId(id)
				.setAdmin(isAdmin)
				.setName(roleName)
				.setPrivileges(dummyPrivilegeSet)
				.setCreatedAt(date)
				.setUpdatedAt(date);
		return role;
	}

}
