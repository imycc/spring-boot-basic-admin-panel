package com.imyc.SBAP.Http.user.action;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.imyc.SBAP.Exception.WebPageNotFoundException;
import com.imyc.SBAP.Http.user.service.UserDatatableProvider;
import com.imyc.SBAP.Http.user.viewobject.UserReadVO;
import com.imyc.SBAP.factories.dummy.user.DummyUserReadVOFactory;


public class UserReadTest {

	@Mock
	private UserDatatableProvider userDatatableProvider;
	private UserRead userRead;
	private UserReadVO dummyUserReadVO;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		userRead = new UserRead(userDatatableProvider);
		dummyUserReadVO = new UserReadVO();
	}

	@Test
	public void testHandleWithUserExist() throws WebPageNotFoundException {
		int id = 1;
		
		dummyUserReadVO = new DummyUserReadVOFactory().make();

		ModelAndView expected = new ModelAndView("admin-panel/user/read", "userReadVO", dummyUserReadVO);
		
		try {
			Mockito.when(userDatatableProvider.loadUserForUserRead(id)).thenReturn(dummyUserReadVO);
		} catch (WebPageNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView actual = userRead.handle(id);
		
		assertNotNull(actual);
		assertEquals(expected.getViewName(), actual.getViewName());
		assertEquals(expected.getModel(), actual.getModel());
	}
	
	@Test
	public void testHandleWithUserIsEmpty() throws WebPageNotFoundException {
		int id = 1;

		ModelAndView expected = new ModelAndView("admin-panel/error/404");
		
		Mockito.when(userDatatableProvider.loadUserForUserRead(id)).thenReturn(null);

		ModelAndView actual = userRead.handle(id);
		
		assertNotNull(actual);
		assertEquals(expected.getViewName(), actual.getViewName());
	}

}
