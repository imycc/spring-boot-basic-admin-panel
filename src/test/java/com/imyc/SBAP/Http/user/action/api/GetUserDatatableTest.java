package com.imyc.SBAP.Http.user.action.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.imyc.SBAP.Http.user.service.UserDatatableProvider;

public class GetUserDatatableTest {

	@Mock
	private UserDatatableProvider userDatatableProvider;
	private GetUserDatatable getUserDatatable;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		getUserDatatable = new GetUserDatatable(userDatatableProvider);
	}

	@Test
	public void testGetUsersForDatatable() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		HashMap<String, Object> map = new HashMap<>();
		map.put("key", "value");
		list.add(map);
		
		Mockito.when(userDatatableProvider.loadAllUserForDatatable()).thenReturn(list);
		List<Map<String,Object>> actual = getUserDatatable.getUsersForDatatable();
		
		assertNotNull(actual);
		assertEquals(list, actual);
	}

}
