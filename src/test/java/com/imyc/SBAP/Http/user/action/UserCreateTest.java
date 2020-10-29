package com.imyc.SBAP.Http.user.action;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.imyc.SBAP.Http.user.services.Requester.UserDatatableProvider;
import com.imyc.SBAP.Http.user.viewobject.UserCreateVO;
import com.imyc.SBAP.factories.dummy.user.DummyUserCreateVOFactory;


public class UserCreateTest {
	
	@Mock
	private UserDatatableProvider userDatatableProvider;
	private UserCreate userCreate;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		userCreate = new UserCreate(userDatatableProvider);
	}

	@Test
	public void testHandle() {
		UserCreateVO dummyUserCreateVO = new DummyUserCreateVOFactory().make();

		ModelAndView expected = new ModelAndView("admin-panel/user/create", "userCreateVO" , dummyUserCreateVO);
		
		Mockito.when(userDatatableProvider.loadRoleListForUserCreate()).thenReturn(dummyUserCreateVO);
		ModelAndView actual = userCreate.handle();
		
		assertNotNull(actual);
		assertEquals(expected.getViewName(), actual.getViewName());
		assertEquals(expected.getModel(), actual.getModel());
	}

}
