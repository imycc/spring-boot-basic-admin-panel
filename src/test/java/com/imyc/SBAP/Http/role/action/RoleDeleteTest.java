package com.imyc.SBAP.Http.role.action;

import com.imyc.SBAP.Exception.web.WebDeleteDataException;
import com.imyc.SBAP.Http.role.service.requester.contracts.RoleDeleteRequester;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoleDeleteTest {

    @Mock
    private RoleDeleteRequester roleDeleteRequester;
    private RoleDelete roleDelete;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        roleDelete = new RoleDelete(roleDeleteRequester);
    }

    @Test
    public void testHandleWithRoleExist() throws WebDeleteDataException {
        int id = 1;

        Mockito.when(roleDeleteRequester.deleteRequest(id)).thenReturn(true);
        String actual = roleDelete.handle(id);

        assertNotNull(actual);
        assertEquals("redirect:/role?delete=success", actual);
    }

}
