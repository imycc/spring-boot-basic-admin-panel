package com.imyc.SBAP.factories.dummy.role;

import com.imyc.SBAP.Http.privilege.viewobject.PrivilegeVO;
import com.imyc.SBAP.Http.role.viewobject.RoleCreateVO;
import com.imyc.SBAP.factories.dummy.DummyFactory;
import com.imyc.SBAP.factories.dummy.privilege.DummyPrivilegeVOFactory;

import java.util.ArrayList;
import java.util.List;

public class DummyRoleCreateVOFactory implements DummyFactory<RoleCreateVO> {

    private RoleCreateVO roleCreateVO;
    private List<PrivilegeVO> dummyPrivilegeVOList;

    public DummyRoleCreateVOFactory() {
        PrivilegeVO dummyPrivilegeVO = new DummyPrivilegeVOFactory().make();

        dummyPrivilegeVOList = new ArrayList<>();
        dummyPrivilegeVOList.add(dummyPrivilegeVO);
    }

    @Override
    public RoleCreateVO make() {

        roleCreateVO = new RoleCreateVO();
        roleCreateVO.setPrivilegeList(dummyPrivilegeVOList);

        return roleCreateVO;
    }

}