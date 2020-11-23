package com.imyc.SBAP.factories.dummy.privilege;

import com.imyc.SBAP.Http.privilege.viewobject.PrivilegeVO;
import com.imyc.SBAP.factories.dummy.DummyFactory;

public class DummyPrivilegeVOFactory implements DummyFactory<PrivilegeVO> {

    private PrivilegeVO dummyPrivilege;

    @Override
    public PrivilegeVO make() {

        dummyPrivilege = new PrivilegeVO();
        dummyPrivilege
                .setId(1)
                .setName("User_TEST");

        return dummyPrivilege;
    }
}
