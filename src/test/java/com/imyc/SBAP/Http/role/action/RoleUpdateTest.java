package com.imyc.SBAP.Http.role.action;

import com.imyc.SBAP.Exception.web.WebPageNotFoundException;
import com.imyc.SBAP.Exception.web.WebUpdateDataException;
import com.imyc.SBAP.Http.role.dto.RoleUpdateDTO;
import com.imyc.SBAP.Http.role.service.requester.contracts.RoleUpdateRequester;
import com.imyc.SBAP.Http.role.viewobject.RoleUpdateVO;
import com.imyc.SBAP.factories.dummy.role.DummyRoleUpdateDTOFactory;
import com.imyc.SBAP.factories.dummy.role.DummyRoleUpdateVOFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoleUpdateTest {

    @Mock
    private RoleUpdateRequester roleUpdateRequester;
    private RoleUpdate roleUpdate;
    private int id = 1;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        roleUpdate = new RoleUpdate(roleUpdateRequester);
    }

    @Test
    public void testRender() throws WebPageNotFoundException {
        RoleUpdateVO dummyRoleUpdateVO = new DummyRoleUpdateVOFactory().make();

        ModelAndView expected = new ModelAndView("admin-panel/role/update");
        expected.addObject("id", id);
        expected.addObject("roleUpdateVO", dummyRoleUpdateVO);
        expected.addObject("roleUpdateDTO", new RoleUpdateDTO());

        Mockito.when(roleUpdateRequester.updateResponse(id)).thenReturn(dummyRoleUpdateVO);
        ModelAndView actual = roleUpdate.render(id);

        assertNotNull(actual);
        assertEquals(expected.getViewName(), actual.getViewName());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void testHandle() throws WebUpdateDataException {

        RoleUpdateDTO dummyRoleUpdateDTO = new DummyRoleUpdateDTOFactory().make();

        Mockito.when(roleUpdateRequester.updateRequest(dummyRoleUpdateDTO, id)).thenReturn(true);
        String actual = roleUpdate.handle(dummyRoleUpdateDTO, id);

        String expected = "redirect:/role?update=success";

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}
