package com.imyc.SBAP.factories.dummy.user;

import com.imyc.SBAP.Http.privilege.dao.Privilege;
import com.imyc.SBAP.Http.user.viewobject.UserVO;
import com.imyc.SBAP.factories.dummy.DummyFactory;
import com.imyc.SBAP.factories.dummy.privilege.DummyPrivilegeFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DummyUserVOFactory implements DummyFactory<UserVO> {

    private UserVO userVO;
    private Date date;
    private String[] roles = {"ADMIN"};
    private List<String> dummyPrivileges;

    public DummyUserVOFactory() {
        this.date = currentDate();
        Privilege privilege = new DummyPrivilegeFactory(1, "User_test").make();
        dummyPrivileges = new ArrayList<>();
        dummyPrivileges.add(privilege.getName());
    }

    @Override
    public UserVO make() {
        userVO = new UserVO();
        userVO
                .setId(1)
                .setUsername("Test username")
                .setName("Test")
                .setPassword("test")
                .setDisabled(false)
                .setAccountExpired(false)
                .setAccountLocked(false)
                .setCredentialsExpired(false)
                .setCreatedAt(date)
                .setUpdatedAt(date)
                .setRoles(roles)
                .setPrivilege(dummyPrivileges);

        return userVO;
    }
}
