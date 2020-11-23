package com.imyc.SBAP.factories.dummy.role;

import com.imyc.SBAP.Http.role.viewobject.RoleVO;
import com.imyc.SBAP.factories.dummy.DummyFactory;

public class DummyRoleVOFactory implements DummyFactory<RoleVO>{

	private RoleVO roleVO;
	private String roleName;
	private int id;
	
	public DummyRoleVOFactory(int id, String roleName) {
		this.roleName = roleName;  
		this.id = id;
	}
	
	@Override
	public RoleVO make() {
		roleVO = new RoleVO();
		roleVO
			.setId(id)
			.setName(roleName);
		return roleVO;
	}

}
