package com.imyc.SBAP.Http.user.action;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.imyc.SBAP.Http.user.service.UserDatatableProvider;
import com.imyc.SBAP.Exception.web.WebDeleteDataException;
import com.imyc.SBAP.Exception.web.WebPageNotFoundException;

class UserDeleteTest {

	@Mock
	private UserDatatableProvider userDatatableProvider;
	private UserDelete userDelete;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		userDelete = new UserDelete(userDatatableProvider);
	}

	@Test
	public void testHandleWithUserExist() throws WebDeleteDataException {
		int id = 1;
		
		Mockito.when(userDatatableProvider.deleteUser(id)).thenReturn(true);
		String actual = userDelete.handle(id);
		
		assertNotNull(actual);
		assertEquals("redirect:/user?delete=success", actual);
	}
	
	@Test
	public void testHandleWithUserIsEmpty() throws WebPageNotFoundException {
//		int id = 1;
//
//		Mockito.when(userDatatableProvider.loadUserForUserRead(id)).thenReturn(null);
//		
//		Exception exception = assertThrows(WebPageNotFoundException.class, () -> {
//			userRead.handle(id);
//	    });
//		
//	    String expectedMessage = "Not Found - 404";
//	    String actualMessage = exception.getMessage();
//
//		
//	    assertTrue(actualMessage.contains(expectedMessage));
	}

}
