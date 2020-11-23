package com.imyc.SBAP.factories.dummy.role;

import com.imyc.SBAP.Http.privilege.viewobject.PrivilegeVO;
import com.imyc.SBAP.Http.role.viewobject.RoleCreateVO;
import com.imyc.SBAP.factories.dummy.DummyFactory;
import com.imyc.SBAP.factories.dummy.privilege.DummyPrivilegeVOFactory;

import java.util.ArrayList;
import java.util.List;

public class DummyRoleCreateVOFactory implements DummyFactory<RoleCreateVO> {

    private RoleCreateVO roleCreateVO;
    private List<PrivilegeVO> privilegeVOList;

    public DummyRoleCreateVOFactory() {
        PrivilegeVO privilegeVO = new DummyPrivilegeVOFactory().make();

        privilegeVOList = new ArrayList<>();
        privilegeVOList.add(privilegeVO);
    }

    @Override
    public RoleCreateVO make() {

        roleCreateVO = new RoleCreateVO();
        roleCreateVO.setPrivilegeList(privilegeVOList);

        return roleCreateVO;
    }

}