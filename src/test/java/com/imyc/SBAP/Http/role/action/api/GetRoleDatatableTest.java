package com.imyc.SBAP.Http.role.action.api;

import com.imyc.SBAP.Http.role.service.requester.contracts.RoleIndexRequester;
import com.imyc.SBAP.Http.role.viewobject.RoleDatatableVO;
import com.imyc.SBAP.factories.dummy.role.DummyRoleDatatableVOFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetRoleDatatableTest {

	@Mock
	private RoleIndexRequester roleIndexRequester;
	private GetRoleDatatable getRoleDatatable;
	private HashMap<String, Object> serverSideConfig;
	private RoleDatatableVO dummyRoleDatatableVO;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		getRoleDatatable = new GetRoleDatatable(roleIndexRequester);
		serverSideConfig = new HashMap<>();
		
		serverSideConfig.put("draw", 1);
		serverSideConfig.put("start", 0);
		serverSideConfig.put("length", 10);
	}

	@Test
	public void testGetUserDatatableWithOutKeyword() {

		dummyRoleDatatableVO = new DummyRoleDatatableVOFactory().make();
		
		serverSideConfig.put("keyword", "");
		Mockito.when(roleIndexRequester.indexResponse(serverSideConfig)).thenReturn(dummyRoleDatatableVO);
		ResponseEntity<RoleDatatableVO> actual = getRoleDatatable.handle(1, 0, 10 ,"");

		ResponseEntity<RoleDatatableVO> expected = new ResponseEntity<>(dummyRoleDatatableVO, HttpStatus.OK);
		
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

}
