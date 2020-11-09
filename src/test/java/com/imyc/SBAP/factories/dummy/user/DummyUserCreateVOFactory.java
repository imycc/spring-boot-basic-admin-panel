package com.imyc.SBAP.factories.dummy.user;

import java.util.ArrayList;
import java.util.List;

import com.imyc.SBAP.Http.role.viewobject.RoleVO;
import com.imyc.SBAP.Http.user.viewobject.UserCreateVO;
import com.imyc.SBAP.factories.dummy.DummyFactory;
import com.imyc.SBAP.factories.dummy.role.DummyRoleVOFactory;

public class DummyUserCreateVOFactory implements DummyFactory<UserCreateVO>{

	private UserCreateVO userCreateVO;
	private List<RoleVO> roleVOList;
	
	public DummyUserCreateVOFactory() {
		RoleVO adminRoleVO = new DummyRoleVOFactory(1, "ADMIN").make();
		RoleVO userRoleVO = new DummyRoleVOFactory(2, "USER").make();

		roleVOList = new ArrayList<RoleVO>();
		roleVOList.add(adminRoleVO);
		roleVOList.add(userRoleVO);
	}

	@Override
	public UserCreateVO make() {
		
		userCreateVO = new UserCreateVO();
		userCreateVO.setRoleVOList(roleVOList);
		
		return userCreateVO;
	}
	
}
