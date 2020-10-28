package com.imyc.SBAP.Http.user.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.imyc.SBAP.Http.user.dao.UserDatatableDAO;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import com.imyc.SBAP.Http.user.viewobject.UserReadVO;
import com.imyc.SBAP.Exception.web.WebDeleteDataException;
import com.imyc.SBAP.Exception.web.WebPageNotFoundException;
import com.imyc.SBAP.factories.dummy.user.DummyUserDatatableVOFactory;
import com.imyc.SBAP.factories.dummy.user.DummyUserReadVOFactory;

public class UserDatatableProviderImplTest {

	@Mock
	private UserDatatableDAO userDatatableDAO;
	private HashMap<String, Object> serverSideConfig;
	private UserDatatableVO dummyUserDatatableVO;
	private UserReadVO dummyUserReadVO;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		serverSideConfig = new HashMap<>();
		serverSideConfig.put("draw", 1);
		serverSideConfig.put("start", 0);
		serverSideConfig.put("length", 10);
	}
	
	
	//Index
	
	@Test
	public void testGetAllUserForDatatable() {

		dummyUserDatatableVO = new DummyUserDatatableVOFactory().make();
		
		Mockito.when(userDatatableDAO.getUserDatatableVO(serverSideConfig)).thenReturn(dummyUserDatatableVO);
		UserDatatableVO actual = new UserDatatableProviderImpl(userDatatableDAO).loadAllUserForDatatable(serverSideConfig);
		
		assertNotNull(actual);
		assertEquals(dummyUserDatatableVO, actual);
	}
	
	//Read
	
	@Test
	public void testLoadUserForUserRead() throws WebPageNotFoundException {
		int id = 1;
		
		dummyUserReadVO = new DummyUserReadVOFactory().make();
		
		Optional<UserReadVO> dummyOptionalUserReadVO = Optional.of(dummyUserReadVO);
		
		Mockito.when(userDatatableDAO.getUserDetailForRead(1)).thenReturn(dummyOptionalUserReadVO);
		UserReadVO actual = new UserDatatableProviderImpl(userDatatableDAO).loadUserForUserRead(id);

		assertNotNull(actual);
	}
	
	@Test
	public void testLoadUserForUserReadIsEmpty() throws WebPageNotFoundException {
		int id = 1;

		Mockito.when(userDatatableDAO.getUserDetailForRead(1)).thenReturn(Optional.empty());
		
		Exception exception = assertThrows(WebPageNotFoundException.class, () -> {
			new UserDatatableProviderImpl(userDatatableDAO).loadUserForUserRead(id);
	    });
		
	    String expectedMessage = "Not Found - 404";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	//Delete
	
	@Test
	public void testDeleteUser() throws WebDeleteDataException {
		int id = 1;
		
		Mockito.when(userDatatableDAO.deleteUserWithRelationById(id)).thenReturn(true);
		
		boolean actual = new UserDatatableProviderImpl(userDatatableDAO).deleteUser(id);
		
		assertTrue(actual);
	}
	
	@Test
	public void testDeleteUserWithDeleteFail() throws WebDeleteDataException {
		int id = 1;

		Mockito.when(userDatatableDAO.deleteUserWithRelationById(id)).thenReturn(false);
		
		Exception exception = assertThrows(WebDeleteDataException.class, () -> {
			new UserDatatableProviderImpl(userDatatableDAO).deleteUser(id);
	    });
		
	    String expectedMessage = "Unable to delete item: " + id;
	    String actualMessage = exception.getMessage();

		
	    assertTrue(actualMessage.contains(expectedMessage));
	}
}
