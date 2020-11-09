package com.imyc.SBAP.factories.dummy.user;

import com.imyc.SBAP.Http.role.viewobject.RoleVO;
import com.imyc.SBAP.Http.user.viewobject.UserUpdateVO;
import com.imyc.SBAP.factories.dummy.DummyFactory;
import com.imyc.SBAP.factories.dummy.role.DummyRoleVOFactory;

import java.util.ArrayList;
import java.util.List;

public class DummyUserUpdateVOFactory implements DummyFactory<UserUpdateVO> {

    private UserUpdateVO userUpdateVO;
    private List<Integer> userRoles;
    private List<RoleVO> roleVOList;

    public DummyUserUpdateVOFactory() {
        userRoles = new ArrayList<Integer>();
        userRoles.add(1);

        RoleVO adminRoleVO = new DummyRoleVOFactory(1, "ADMIN").make();
        RoleVO userRoleVO = new DummyRoleVOFactory(2, "USER").make();
        roleVOList = new ArrayList<RoleVO>();
        roleVOList.add(adminRoleVO);
        roleVOList.add(userRoleVO);
    }

    @Override
    public UserUpdateVO make() {

        userUpdateVO = new UserUpdateVO();
        userUpdateVO
                .setName("Test Name")
                .setUsername("TestUsername")
                .setEmail("test@test.com")
                .setRoleVOList(roleVOList);

        return userUpdateVO;
    }
}
