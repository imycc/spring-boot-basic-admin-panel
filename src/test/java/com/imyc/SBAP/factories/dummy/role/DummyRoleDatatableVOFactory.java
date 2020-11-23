package com.imyc.SBAP.factories.dummy.role;

import com.imyc.SBAP.Http.role.viewobject.RoleDatatableVO;
import com.imyc.SBAP.Http.role.viewobject.datatable.RoleRow;
import com.imyc.SBAP.factories.dummy.DummyFactory;

import java.util.ArrayList;
import java.util.List;

public class DummyRoleDatatableVOFactory implements DummyFactory<RoleDatatableVO> {

    private RoleDatatableVO roleDatatableVO;
    private List<RoleRow> dummyRoleRowList;

    public DummyRoleDatatableVOFactory() {

        RoleRow dummyRoleRow = new DummyRoleRowFactory().make();
        dummyRoleRowList = new ArrayList<>();
        dummyRoleRowList.add(dummyRoleRow);

        roleDatatableVO = new RoleDatatableVO();
    }

    @Override
    public RoleDatatableVO make() {

        roleDatatableVO
                .setDraw(1)
                .setRecordsFiltered((long) 1) //dummyUserRowList only have 1 record;
                .setRecordsTotal((long) 1) //dummyUserRowList only have 1 record;
                .setData(dummyRoleRowList);

        return roleDatatableVO;
    }

}
