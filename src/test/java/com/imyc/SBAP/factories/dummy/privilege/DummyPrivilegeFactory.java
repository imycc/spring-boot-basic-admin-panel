package com.imyc.SBAP.factories.dummy.privilege;

import com.imyc.SBAP.Http.privilege.dao.Privilege;
import com.imyc.SBAP.factories.dummy.DummyFactory;

import java.util.Date;

public class DummyPrivilegeFactory implements DummyFactory<Privilege> {

    private int id;
    private String name;
    private Date date;

    public DummyPrivilegeFactory(int id, String name) {
        this.id = id;
        this.name = name;
        this.date = currentDate();
    }

    @Override
    public Privilege make() {
        Privilege privilege = new Privilege();
        privilege
                .setId(id)
                .setName(name)
                .setCreated_at(date)
                .setUpdated_at(date);

        return privilege;
    }
}
