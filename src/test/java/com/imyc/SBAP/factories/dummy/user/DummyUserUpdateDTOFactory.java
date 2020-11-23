package com.imyc.SBAP.factories.dummy.user;

import com.imyc.SBAP.Http.user.dto.UserUpdateDTO;
import com.imyc.SBAP.factories.dummy.DummyFactory;

import java.util.ArrayList;
import java.util.List;

public class DummyUserUpdateDTOFactory implements DummyFactory<UserUpdateDTO> {

    private UserUpdateDTO userUpdateDTO;
    private List<Integer> roles;

    public DummyUserUpdateDTOFactory() {
        roles = new ArrayList<>();
        roles.add(1);
    }

    @Override
    public UserUpdateDTO make() {


        userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO
                .setName("Test Name")
                .setUsername("TestUsername")
                .setEmail("test@test.com")
                .setRoles(roles);

        return userUpdateDTO;
    }
}