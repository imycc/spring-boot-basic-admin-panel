package com.imyc.SBAP.factories.dummy.role;

import com.imyc.SBAP.Http.role.dto.RoleUpdateDTO;
import com.imyc.SBAP.Http.user.dto.UserUpdateDTO;
import com.imyc.SBAP.factories.dummy.DummyFactory;

import java.util.ArrayList;
import java.util.List;

public class DummyRoleUpdateDTOFactory implements DummyFactory<RoleUpdateDTO> {

    private RoleUpdateDTO roleUpdateDTO;
    private List<Integer> privileges;

    public DummyRoleUpdateDTOFactory() {
        privileges = new ArrayList<>();
        privileges.add(1);
    }

    @Override
    public RoleUpdateDTO make() {

        roleUpdateDTO = new RoleUpdateDTO();
        roleUpdateDTO
                .setName("Test Name")
                .setAdmin(true)
                .setPrivileges(privileges);

        return roleUpdateDTO;
    }
}
