package com.imyc.SBAP.Http.user.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.imyc.SBAP.Exception.web.WebCreateDataException;
import com.imyc.SBAP.Http.user.dto.UserCreateDTO;
import com.imyc.SBAP.Http.user.services.requester.contracts.UserCreateRequester;
import com.imyc.SBAP.Http.user.viewobject.UserCreateVO;
import com.imyc.SBAP.factories.dummy.user.DummyUserCreateVOFactory;


public class UserCreateTest {
	
	@Mock
	private UserCreateRequester userCreateRequester;
	private UserCreate userCreate;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		userCreate = new UserCreate(userCreateRequester);
	}

	@Test
	public void testRender() {
		UserCreateVO dummyUserCreateVO = new DummyUserCreateVOFactory().make();

		ModelAndView expected = new ModelAndView("admin-panel/user/create");
		expected.addObject("userCreateVO", dummyUserCreateVO);
		expected.addObject("userCreateDTO", new UserCreateDTO());
		
		Mockito.when(userCreateRequester.createResponse()).thenReturn(dummyUserCreateVO);
		ModelAndView actual = userCreate.render();
		
		assertNotNull(actual);
		assertEquals(expected.getViewName(), actual.getViewName());
	}
	
	@Test
	public void testHandle() throws WebCreateDataException {
		UserCreateDTO dummyUserCreateDTO = new UserCreateDTO();
		
		String expected = "redirect:/user?create=success";
		
		Mockito.when(userCreateRequester.createRequest(dummyUserCreateDTO)).thenReturn(true);
		String actual = userCreate.handle(dummyUserCreateDTO);
		
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

}
