package com.imyc.SBAP.Http.role.action;

import com.imyc.SBAP.Exception.web.WebCreateDataException;
import com.imyc.SBAP.Http.role.dto.RoleCreateDTO;
import com.imyc.SBAP.Http.role.service.requester.contracts.RoleCreateRequester;
import com.imyc.SBAP.Http.role.viewobject.RoleCreateVO;
import com.imyc.SBAP.Http.user.dto.UserCreateDTO;
import com.imyc.SBAP.factories.dummy.role.DummyRoleCreateVOFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoleCreateTest {

    @Mock
    private RoleCreateRequester roleCreateRequester;
    private RoleCreate roleCreate;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        roleCreate = new RoleCreate(roleCreateRequester);
    }

    @Test
    public void testRender() {
        RoleCreateVO dummyRoleCreateVO = new DummyRoleCreateVOFactory().make();

        ModelAndView expected = new ModelAndView("admin-panel/role/create");
        expected.addObject("roleCreateVO", dummyRoleCreateVO);
        expected.addObject("roleCreateDTO", new UserCreateDTO());

        Mockito.when(roleCreateRequester.createResponse()).thenReturn(dummyRoleCreateVO);
        ModelAndView actual = roleCreate.render();

        assertNotNull(actual);
        assertEquals(expected.getViewName(), actual.getViewName());
    }

    @Test
    public void testHandle() throws WebCreateDataException {
        RoleCreateDTO dummyRoleCreateDTO = new RoleCreateDTO();

        String expected = "redirect:/role?create=success";

        Mockito.when(roleCreateRequester.createRequest(dummyRoleCreateDTO)).thenReturn(true);
        String actual = roleCreate.handle(dummyRoleCreateDTO);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}
