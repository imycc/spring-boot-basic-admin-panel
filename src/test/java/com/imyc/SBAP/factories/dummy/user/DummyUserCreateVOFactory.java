package com.imyc.SBAP.factories.dummy.user;

import java.util.ArrayList;
import java.util.List;

import com.imyc.SBAP.Http.role.viewobject.RoleVO;
import com.imyc.SBAP.Http.user.viewobject.UserCreateVO;
import com.imyc.SBAP.factories.dummy.DummyFactory;
import com.imyc.SBAP.factories.dummy.role.DummyRoleVOFactory;

public class DummyUserCreateVOFactory implements DummyFactory<UserCreateVO>{

	private UserCreateVO userCreateVO;
	
	public DummyUserCreateVOFactory() {}

	@Override
	public UserCreateVO make() {
		
		RoleVO adminRoleVO = new DummyRoleVOFactory(1, "Admin").make();
		RoleVO userRoleVO = new DummyRoleVOFactory(2, "User").make();
		
		List<RoleVO> roleVOList = new ArrayList<RoleVO>();
		roleVOList.add(adminRoleVO);
		roleVOList.add(userRoleVO);
		
		userCreateVO = new UserCreateVO();
		userCreateVO.setRoleVOList(roleVOList);
		
		return userCreateVO;
	}
	
}
