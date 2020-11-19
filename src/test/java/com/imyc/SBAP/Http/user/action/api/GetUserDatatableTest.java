package com.imyc.SBAP.Http.user.action.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import java.util.HashMap;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import com.imyc.SBAP.factories.dummy.base.DummyDatatableServerSideConfigFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.imyc.SBAP.Http.user.services.requester.contracts.UserIndexRequester;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import com.imyc.SBAP.factories.dummy.user.DummyUserDatatableVOFactory;

public class GetUserDatatableTest {

	@Mock
	private UserIndexRequester userIndexContract;
	private GetUserDatatable getUserDatatable;
	private UserDatatableVO dummyUserDatatableVO;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		getUserDatatable = new GetUserDatatable(userIndexContract);
	}

	@Test
	public void testGetUserDatatableWithOutKeyword() {
		
		dummyUserDatatableVO = new DummyUserDatatableVOFactory().make();
		
		Mockito.when(userIndexContract.indexResponse(any(DatatableServerSideConfig.class))).thenReturn(dummyUserDatatableVO);
		ResponseEntity<UserDatatableVO> actual = getUserDatatable.handle(1, 0, 10 ,"");

		ResponseEntity<UserDatatableVO> expected = new ResponseEntity<UserDatatableVO>(dummyUserDatatableVO, HttpStatus.OK);
		
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

}
