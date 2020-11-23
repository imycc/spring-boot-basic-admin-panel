package com.imyc.SBAP.factories.dummy.role;

import com.imyc.SBAP.Http.role.viewobject.datatable.RoleRow;
import com.imyc.SBAP.factories.dummy.DummyFactory;

public class DummyRoleRowFactory implements DummyFactory<RoleRow> {

    private RoleRow roleRow;

    public DummyRoleRowFactory() {
        roleRow = new RoleRow();
    }

    @Override
    public RoleRow make() {
        roleRow
                .setId(1)
                .setName("test");

        return roleRow;
    }
}