package com.imyc.SBAP.Http.user.action.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.imyc.SBAP.Http.user.services.UserDatatableProvider;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import com.imyc.SBAP.factories.dummy.user.DummyUserDatatableVOFactory;

public class GetUserDatatableTest {

	@Mock
	private UserDatatableProvider userDatatableProvider;
	private GetUserDatatable getUserDatatable;
	private HashMap<String, Object> serverSideConfig;
	private UserDatatableVO dummyUserDatatableVO;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		getUserDatatable = new GetUserDatatable(userDatatableProvider);
		serverSideConfig = new HashMap<>();
		
		serverSideConfig.put("draw", 1);
		serverSideConfig.put("start", 0);
		serverSideConfig.put("length", 10);
	}

	@Test
	public void testGetUserDatatableWithOutKeyword() {
		
		dummyUserDatatableVO = new DummyUserDatatableVOFactory().make();
		
		serverSideConfig.put("keyword", "");
		Mockito.when(userDatatableProvider.loadAllUserForDatatable(serverSideConfig)).thenReturn(dummyUserDatatableVO);
		ResponseEntity<UserDatatableVO> actual = getUserDatatable.handle(1, 0, 10 ,"");

		ResponseEntity<UserDatatableVO> expected = new ResponseEntity<UserDatatableVO>(dummyUserDatatableVO, HttpStatus.OK);
		
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

}
