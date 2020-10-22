package com.imyc.SBAP.Http.user.action.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.imyc.SBAP.Http.user.persistent.object.UserDatatablePO;
import com.imyc.SBAP.Http.user.service.UserCrudProvider;

public class GetUserDatatableTest {

	@Mock
	private UserCrudProvider userCrudProvider;
	private UserDatatablePO userDatatablePO;
	private GetUserDatatable getUserDatatable;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		getUserDatatable = new GetUserDatatable();
	}

	@Test
	public void testGetUsersForDatatable() {
		HashMap<String, Object> expected = new HashMap<>();
		expected.put("key", "value");
		
		Mockito.when(userCrudProvider.loadAllUserForDatatable()).thenReturn(userDatatablePO);
		
		Map<String,Object> actual = getUserDatatable.getUsersForDatatable();
		
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

}
