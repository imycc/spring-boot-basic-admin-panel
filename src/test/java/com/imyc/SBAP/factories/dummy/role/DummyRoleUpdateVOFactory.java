package com.imyc.SBAP.factories.dummy.role;

import com.imyc.SBAP.Http.privilege.viewobject.PrivilegeVO;
import com.imyc.SBAP.Http.role.viewobject.RoleUpdateVO;
import com.imyc.SBAP.factories.dummy.DummyFactory;
import com.imyc.SBAP.factories.dummy.privilege.DummyPrivilegeVOFactory;

import java.util.ArrayList;
import java.util.List;

public class DummyRoleUpdateVOFactory implements DummyFactory<RoleUpdateVO> {

    private RoleUpdateVO roleUpdateVO;
    private List<Integer> rolePrivileges;
    private List<PrivilegeVO> dummyPrivilegeVOList;

    public DummyRoleUpdateVOFactory() {
        rolePrivileges = new ArrayList<>();
        rolePrivileges.add(1);

        PrivilegeVO dummyPrivilegeVO = new DummyPrivilegeVOFactory().make();
        dummyPrivilegeVOList = new ArrayList<>();
        dummyPrivilegeVOList.add(dummyPrivilegeVO);
    }

    @Override
    public RoleUpdateVO make() {

        roleUpdateVO = new RoleUpdateVO();
        roleUpdateVO
                .setIsAdmin(true)
                .setName("Test Name")
                .setPrivilegeList(dummyPrivilegeVOList);

        return roleUpdateVO;
    }
}
