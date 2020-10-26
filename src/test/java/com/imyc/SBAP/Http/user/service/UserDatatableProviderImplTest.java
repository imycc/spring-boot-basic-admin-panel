package com.imyc.SBAP.Http.user.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.imyc.SBAP.Http.user.dao.UserDatatableDAO;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import com.imyc.SBAP.Http.user.viewobject.datatable.UserRow;

public class UserDatatableProviderImplTest {

	@Mock
	private UserDatatableDAO userDatatableDAO;
	private HashMap<String, Object> serverSideConfig;
	private UserDatatableVO dummyUserDatatableVO;
	private List<UserRow> userRowList;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		serverSideConfig = new HashMap<>();
		serverSideConfig.put("draw", 1);
		serverSideConfig.put("start", 0);
		serverSideConfig.put("length", 10);
		
		dummyUserDatatableVO = new UserDatatableVO();
		userRowList = new ArrayList<UserRow>();
	}
	
	@Test
	public void expectGetAllUserForDatatable() {
		
		userRowList.add(
			new UserRow()
				.setId(1)
				.setName("test")
				.setEmail("test@test.com")
				.setDisabled(false)
		);
			
		dummyUserDatatableVO
			.setDraw(1)
			.setRecordsFiltered((long) 10)
			.setRecordsTotal((long) 10)
			.setData(userRowList);

		
		Mockito.when(userDatatableDAO.getUserDatatableVO(serverSideConfig)).thenReturn(dummyUserDatatableVO);
		UserDatatableVO actual = new UserDatatableProviderImpl(userDatatableDAO).loadAllUserForDatatable(serverSideConfig);
		
		assertNotNull(actual);
		assertEquals(dummyUserDatatableVO, actual);
	}
	

}
