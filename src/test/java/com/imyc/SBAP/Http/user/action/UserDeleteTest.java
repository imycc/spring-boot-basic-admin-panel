package com.imyc.SBAP.Http.user.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.imyc.SBAP.Exception.web.WebDeleteDataException;
import com.imyc.SBAP.Http.user.services.requester.contracts.UserDeleteRequester;

public class UserDeleteTest {

	@Mock
	private UserDeleteRequester userDeleteRequester;
	private UserDelete userDelete;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		userDelete = new UserDelete(userDeleteRequester);
	}

	@Test
	public void testHandleWithUserExist() throws WebDeleteDataException {
		int id = 1;
		
		Mockito.when(userDeleteRequester.deleteRequest(id)).thenReturn(true);
		String actual = userDelete.handle(id);
		
		assertNotNull(actual);
		assertEquals("redirect:/user?delete=success", actual);
	}

}
