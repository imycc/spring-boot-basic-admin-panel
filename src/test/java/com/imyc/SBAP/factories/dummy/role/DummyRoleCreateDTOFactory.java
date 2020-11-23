package com.imyc.SBAP.factories.dummy.role;

import com.imyc.SBAP.Http.role.dto.RoleCreateDTO;
import com.imyc.SBAP.factories.dummy.DummyFactory;

import java.util.ArrayList;
import java.util.List;

public class DummyRoleCreateDTOFactory implements DummyFactory<RoleCreateDTO> {

    private RoleCreateDTO roleCreateDTO;

    @Override
    public RoleCreateDTO make() {
        List<Integer> privileges = new ArrayList<>();
        privileges.add(1);

        roleCreateDTO = new RoleCreateDTO();
        roleCreateDTO
                .setName("Test")
                .setAdmin(true)
                .setPrivileges(privileges);

        return roleCreateDTO;
    }
}
