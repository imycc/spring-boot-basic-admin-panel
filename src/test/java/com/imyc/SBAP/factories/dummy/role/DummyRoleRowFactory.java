package com.imyc.SBAP.factories.dummy.role;

import com.imyc.SBAP.Http.role.viewobject.datatable.RoleRow;
import com.imyc.SBAP.factories.dummy.DummyFactory;

public class DummyRoleRowFactory implements DummyFactory<RoleRow> {

    private RoleRow dummyRoleRow;

    public DummyRoleRowFactory() {
        dummyRoleRow = new RoleRow();
    }

    @Override
    public RoleRow make() {
        dummyRoleRow
                .setId(1)
                .setName("test");

        return dummyRoleRow;
    }
}