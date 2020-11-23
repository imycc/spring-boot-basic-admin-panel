package com.imyc.SBAP.Http.user.services.requester;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import com.imyc.SBAP.Exception.web.WebUpdateDataException;
import com.imyc.SBAP.Http.user.dto.UserUpdateDTO;
import com.imyc.SBAP.Http.user.viewobject.UserUpdateVO;
import com.imyc.SBAP.factories.dummy.base.DummyDatatableServerSideConfigFactory;
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
import com.imyc.SBAP.Http.user.services.dataprocess.UserDatatableDPO;
import com.imyc.SBAP.Http.user.viewobject.UserCreateVO;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import com.imyc.SBAP.Http.user.viewobject.UserReadVO;

public class UserDatatableProviderImplTest {

	@Mock
	private UserDatatableDPO userDatatableDPO;
	private DatatableServerSideConfig dummyDatatableServerSideConfig;
	private UserDatatableVO dummyUserDatatableVO;
	private UserReadVO dummyUserReadVO;
	private int id = 1;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	// Index
	
	@Test
	public void testGetAllUserForDatatable() {

		dummyUserDatatableVO = new DummyUserDatatableVOFactory().make();
		dummyDatatableServerSideConfig = new DummyDatatableServerSideConfigFactory(1, 0, 10, null).make();
		
		Mockito.when(userDatatableDPO.getUserDatatableVO(dummyDatatableServerSideConfig)).thenReturn(dummyUserDatatableVO);
		UserDatatableVO actual = new UserDatatableProvider(userDatatableDPO).indexResponse(dummyDatatableServerSideConfig);
		
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
		
		boolean actual = new UserDatatableProvider(userDatatableDPO).deleteRequest(id);
		
		assertTrue(actual);
	}
	
	@Test
	public void testDeleteUserWithException() throws WebDeleteDataException {

		Mockito.when(userDatatableDPO.deleteUserWithRelationById(id)).thenReturn(false);
		
		Exception exception = assertThrows(WebDeleteDataException.class, () -> {
			new UserDatatableProvider(userDatatableDPO).deleteRequest(id);
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

		Mockito.when(userDatatableDPO.getUserForUpdate(id)).thenReturn(Optional.of(dummyUserUpdateVO));
		UserUpdateVO actual = new UserDatatableProvider(userDatatableDPO).updateResponse(id);

		assertNotNull(actual);
		assertThat(actual).usingRecursiveComparison().isEqualTo(dummyUserUpdateVO);
	}

	@Test
	public void testUpdateRequest() throws WebUpdateDataException {
		UserUpdateDTO dummyUserUpdateDTO = new DummyUserUpdateDTOFactory().make();

		Mockito.when(userDatatableDPO.userUpdate(dummyUserUpdateDTO, id)).thenReturn(true);
		boolean actual = new UserDatatableProvider(userDatatableDPO).updateRequest(dummyUserUpdateDTO, id);

		assertTrue(actual);
	}

	@Test
	public void testUpdateRequestWithException() throws WebUpdateDataException {

		UserUpdateDTO dummyUserUpdateDTO = new DummyUserUpdateDTOFactory().make();

		Mockito.when(userDatatableDPO.userUpdate(dummyUserUpdateDTO, id)).thenReturn(false);

		Exception exception = assertThrows(WebUpdateDataException.class, () -> {
			new UserDatatableProvider(userDatatableDPO).updateRequest(dummyUserUpdateDTO, id);
		});

		String expectedMessage = "Unable to update: " + dummyUserUpdateDTO.getName();
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
}
