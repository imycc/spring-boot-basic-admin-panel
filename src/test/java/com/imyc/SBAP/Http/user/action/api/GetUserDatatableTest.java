package com.imyc.SBAP.Http.user.action.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.imyc.SBAP.Http.user.service.UserDatatableProvider;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import com.imyc.SBAP.Http.user.viewobject.datatable.UserRow;

public class GetUserDatatableTest {

	@Mock
	private UserDatatableProvider userDatatableProvider;
	private GetUserDatatable getUserDatatable;
	private HashMap<String, Object> serverSideConfig;
	private UserDatatableVO dummyUserDatatableVO;
	private List<UserRow> userRowList;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		getUserDatatable = new GetUserDatatable(userDatatableProvider);
		serverSideConfig = new HashMap<>();
		
		serverSideConfig.put("draw", 1);
		serverSideConfig.put("start", 0);
		serverSideConfig.put("length", 10);
		
		dummyUserDatatableVO = new UserDatatableVO();
		userRowList = new ArrayList<UserRow>();
	}

	@Test
	public void testGetUsersForDatatableWithOutKeyword() {
		
		userRowList.add(
			new UserRow()
				.setId(1)
				.setName("test")
				.setEmail("test@test.com")
				.setDisabled(false)
		);
		
		dummyUserDatatableVO
			.setDraw(1)
			.setRecordsFiltered((long) 1)
			.setRecordsTotal((long) 1)
			.setData(userRowList);
		
		serverSideConfig.put("keyword", "");
		Mockito.when(userDatatableProvider.loadAllUserForDatatable(serverSideConfig)).thenReturn(dummyUserDatatableVO);
		ResponseEntity<UserDatatableVO> actual = getUserDatatable.getUsersForDatatable(1, 0, 10 ,"");

		ResponseEntity<UserDatatableVO> expected = new ResponseEntity<UserDatatableVO>(dummyUserDatatableVO, HttpStatus.OK);
		
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

}
