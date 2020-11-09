package com.imyc.SBAP.Http.user.action;

import com.imyc.SBAP.Exception.web.WebPageNotFoundException;
import com.imyc.SBAP.Exception.web.WebUpdateDataException;
import com.imyc.SBAP.Http.user.dto.UserCreateDTO;
import com.imyc.SBAP.Http.user.dto.UserUpdateDTO;
import com.imyc.SBAP.Http.user.services.requester.contracts.UserCreateRequester;
import com.imyc.SBAP.Http.user.services.requester.contracts.UserUpdateRequester;
import com.imyc.SBAP.Http.user.viewobject.UserUpdateVO;
import com.imyc.SBAP.factories.dummy.user.DummyUserUpdateDTOFactory;
import com.imyc.SBAP.factories.dummy.user.DummyUserUpdateVOFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserUpdateTest {

    @Mock
    private UserUpdateRequester userUpdateRequester;
    private UserUpdate userUpdate;
    private int id = 1;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userUpdate = new UserUpdate(userUpdateRequester);
    }

    @Test
    public void testRender() throws WebPageNotFoundException {
        UserUpdateVO dummyUserUpdateVO = new DummyUserUpdateVOFactory().make();

        ModelAndView expected = new ModelAndView("admin-panel/user/update");
        expected.addObject("id", id);
        expected.addObject("userUpdateVO", dummyUserUpdateVO);
        expected.addObject("userUpdateDTO", new UserUpdateDTO());

        Mockito.when(userUpdateRequester.updateResponse(id)).thenReturn(dummyUserUpdateVO);
        ModelAndView actual = userUpdate.render(id);

        assertNotNull(actual);
        assertEquals(expected.getViewName(), actual.getViewName());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void testHandle() throws WebUpdateDataException {

        UserUpdateDTO dummyUserUpdateDTO = new DummyUserUpdateDTOFactory().make();

        Mockito.when(userUpdateRequester.updateRequest(dummyUserUpdateDTO, id)).thenReturn(true);
        String actual = userUpdate.handle(dummyUserUpdateDTO, id);

        String expected = "redirect:/user?update=success";

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

}
