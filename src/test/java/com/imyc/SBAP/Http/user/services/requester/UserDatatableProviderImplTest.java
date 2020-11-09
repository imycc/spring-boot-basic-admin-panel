package com.imyc.SBAP.Http.user.services.requester;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Optional;

import com.imyc.SBAP.Http.user.viewobject.UserUpdateVO;
import com.imyc.SBAP.factories.dummy.user.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.imyc.SBAP.Exception.web.WebCreateDataException;
import com.imyc.SBAP.Exception.web.WebDeleteDataException;
import com.imyc.SBAP.Exception.web.WebPageNotFoundException;
import com.imyc.SBAP.Http.user.dto.UserCreateDTO;
import com.imyc.SBAP.Http.user.services.dpl.UserDatatableDPO;
import com.imyc.SBAP.Http.user.viewobject.UserCreateVO;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import com.imyc.SBAP.Http.user.viewobject.UserReadVO;

public class UserDatatableProviderImplTest {

	@Mock
	private UserDatatableDPO userDatatableDPO;
	private HashMap<String, Object> serverSideConfig;
	private UserDatatableVO dummyUserDatatableVO;
	private UserReadVO dummyUserReadVO;
	private int id = 1;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		serverSideConfig = new HashMap<>();
		serverSideConfig.put("draw", 1);
		serverSideConfig.put("start", 0);
		serverSideConfig.put("length", 10);
	}
	
	// Index
	
	@Test
	public void testGetAllUserForDatatable() {

		dummyUserDatatableVO = new DummyUserDatatableVOFactory().make();
		
		Mockito.when(userDatatableDPO.getUserDatatableVO(serverSideConfig)).thenReturn(dummyUserDatatableVO);
		UserDatatableVO actual = new UserDatatableProvider(userDatatableDPO).indexResponse(serverSideConfig);
		
		assertNotNull(actual);
		assertEquals(dummyUserDatatableVO, actual);
	}
	
	// Read
	
	@Test
	public void testLoadUserForUserRead() throws WebPageNotFoundException {

		dummyUserReadVO = new DummyUserReadVOFactory().make();
		
		Optional<UserReadVO> dummyOptionalUserReadVO = Optional.of(dummyUserReadVO);
		
		Mockito.when(userDatatableDPO.getUserDetailForRead(id)).thenReturn(dummyOptionalUserReadVO);
		UserReadVO actual = new UserDatatableProvider(userDatatableDPO).readResponse(id);

		assertNotNull(actual);
	    assertThat(actual).isInstanceOf(UserReadVO.class);
	}
	
	@Test
	public void testLoadUserForUserReadIsEmpty() throws WebPageNotFoundException {

		Mockito.when(userDatatableDPO.getUserDetailForRead(id)).thenReturn(Optional.empty());
		
		Exception exception = assertThrows(WebPageNotFoundException.class, () -> {
			new UserDatatableProvider(userDatatableDPO).readResponse(id);
	    });
		
	    String expectedMessage = "Not Found - 404";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	// Delete
	
	@Test
	public void testDeleteUser() throws WebDeleteDataException {

		Mockito.when(userDatatableDPO.deleteUserWithRelationById(id)).thenReturn(true);
		
		boolean actual = new UserDatatableProvider(userDatatableDPO).deleteUser(id);
		
		assertTrue(actual);
	}
	
	@Test
	public void testDeleteUserWithException() throws WebDeleteDataException {

		Mockito.when(userDatatableDPO.deleteUserWithRelationById(id)).thenReturn(false);
		
		Exception exception = assertThrows(WebDeleteDataException.class, () -> {
			new UserDatatableProvider(userDatatableDPO).deleteUser(id);
	    });
		
	    String expectedMessage = "Unable to delete: " + id;
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	// Create
	
	@Test
	public void testCreateResponse() {
		
		UserCreateVO dummyUserCreateVO = new DummyUserCreateVOFactory().make();
		
		Mockito.when(userDatatableDPO.getRoleListForUserCreate()).thenReturn(dummyUserCreateVO);
		UserCreateVO actual = new UserDatatableProvider(userDatatableDPO).createResponse();

		assertNotNull(actual);
		assertNotNull(actual.getRoleVOList());
	    assertThat(actual).isInstanceOf(UserCreateVO.class);
	}
	
	@Test
	public void testCreateRequest() throws WebCreateDataException {
		
		UserCreateDTO dummyUserCreateDTO = new DummyUserCreateDTOFactory().make();
		
		Mockito.when(userDatatableDPO.userCreate(dummyUserCreateDTO)).thenReturn(true);
		boolean actual = new UserDatatableProvider(userDatatableDPO).createRequest(dummyUserCreateDTO);

		assertNotNull(actual);
		assertTrue(actual);
	}
	
	@Test
	public void testCreateRequestWithException() throws WebCreateDataException {
		
		UserCreateDTO dummyUserCreateDTO = new DummyUserCreateDTOFactory().make();
		
		Mockito.when(userDatatableDPO.userCreate(dummyUserCreateDTO)).thenReturn(false);
		
		Exception exception = assertThrows(WebCreateDataException.class, () -> {
			new UserDatatableProvider(userDatatableDPO).createRequest(dummyUserCreateDTO);
	    });
		
	    String expectedMessage = "Unable to create: " + dummyUserCreateDTO.getName();
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	    
	}

	// Update

	@Test
	public void testUpdateResponse() throws WebPageNotFoundException {
		UserUpdateVO dummyUserUpdateVO = new DummyUserUpdateVOFactory().make();

		Mockito.when(userDatatableDPO.getUserForUserUpdate(id)).thenReturn(Optional.of(dummyUserUpdateVO));
		UserUpdateVO actual = new UserDatatableProvider(userDatatableDPO).updateResponse(id);

		assertNotNull(actual);
		assertThat(actual).usingRecursiveComparison().isEqualTo(dummyUserUpdateVO);
	}
}
