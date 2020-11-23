package com.imyc.SBAP.Http.role.services.requester;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import com.imyc.SBAP.Exception.web.WebCreateDataException;
import com.imyc.SBAP.Exception.web.WebDeleteDataException;
import com.imyc.SBAP.Exception.web.WebPageNotFoundException;
import com.imyc.SBAP.Exception.web.WebUpdateDataException;
import com.imyc.SBAP.Http.role.dto.RoleCreateDTO;
import com.imyc.SBAP.Http.role.dto.RoleUpdateDTO;
import com.imyc.SBAP.Http.role.service.dataprocess.RoleDatatableDPO;
import com.imyc.SBAP.Http.role.service.requester.RoleDatatableProvider;
import com.imyc.SBAP.Http.role.viewobject.RoleCreateVO;
import com.imyc.SBAP.Http.role.viewobject.RoleDatatableVO;
import com.imyc.SBAP.Http.role.viewobject.RoleUpdateVO;
import com.imyc.SBAP.factories.dummy.base.DummyDatatableServerSideConfigFactory;
import com.imyc.SBAP.factories.dummy.role.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class RoleDatatableProviderTest {

	@Mock
	private RoleDatatableDPO roleDatatableDPO;
	private DatatableServerSideConfig dummyDatatableServerSideConfig;
	private RoleDatatableVO dummyRoleDatatableVO;
	private int id = 1;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	// Index
	
	@Test
	public void testGetAllRoleForDatatable() {

		dummyRoleDatatableVO = new DummyRoleDatatableVOFactory().make();
		dummyDatatableServerSideConfig = new DummyDatatableServerSideConfigFactory(1, 0, 10, null).make();
		
		Mockito.when(roleDatatableDPO.getRoleDatatableVO(dummyDatatableServerSideConfig)).thenReturn(dummyRoleDatatableVO);
		RoleDatatableVO actual = new RoleDatatableProvider(roleDatatableDPO).indexResponse(dummyDatatableServerSideConfig);
		
		assertNotNull(actual);
		assertEquals(dummyRoleDatatableVO, actual);
	}

	// Create
	@Test
	public void testCreateResponse() {

		RoleCreateVO dummyRoleCreateVO = new DummyRoleCreateVOFactory().make();

		Mockito.when(roleDatatableDPO.getPrivilegeList()).thenReturn(dummyRoleCreateVO);
		RoleCreateVO actual = new RoleDatatableProvider(roleDatatableDPO).createResponse();

		assertNotNull(actual);
		assertNotNull(actual.getPrivilegeList());
		assertThat(actual).isInstanceOf(RoleCreateVO.class);
	}

	@Test
	public void testCreateRequest() throws WebCreateDataException {

		RoleCreateDTO dummyRoleCreateDTO = new DummyRoleCreateDTOFactory().make();

		Mockito.when(roleDatatableDPO.roleCreate(dummyRoleCreateDTO)).thenReturn(true);
		boolean actual = new RoleDatatableProvider(roleDatatableDPO).createRequest(dummyRoleCreateDTO);

		assertNotNull(actual);
		assertTrue(actual);
	}

	@Test
	public void testCreateRequestWithException() throws WebCreateDataException {

		RoleCreateDTO dummyRoleCreateDTO = new DummyRoleCreateDTOFactory().make();

		Mockito.when(roleDatatableDPO.roleCreate(dummyRoleCreateDTO)).thenReturn(false);

		Exception exception = assertThrows(WebCreateDataException.class, () -> {
			new RoleDatatableProvider(roleDatatableDPO).createRequest(dummyRoleCreateDTO);
		});

		String expectedMessage = "Unable to create: " + dummyRoleCreateDTO.getName();
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	// Delete

	@Test
	public void testDeleteRole() throws WebDeleteDataException {

		Mockito.when(roleDatatableDPO.deleteRoleWithRelationById(id)).thenReturn(true);

		boolean actual = new RoleDatatableProvider(roleDatatableDPO).deleteRequest(id);

		assertTrue(actual);
	}

	@Test
	public void testDeleteRoleWithException() throws WebDeleteDataException {

		Mockito.when(roleDatatableDPO.deleteRoleWithRelationById(id)).thenReturn(false);

		Exception exception = assertThrows(WebDeleteDataException.class, () -> {
			new RoleDatatableProvider(roleDatatableDPO).deleteRequest(id);
		});

		String expectedMessage = "Unable to delete: " + id;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	// Update

	@Test
	public void testUpdateResponse() throws WebPageNotFoundException {
		RoleUpdateVO dummyRoleUpdateVO = new DummyRoleUpdateVOFactory().make();

		Mockito.when(roleDatatableDPO.getRoleForUpdate(id)).thenReturn(Optional.of(dummyRoleUpdateVO));
		RoleUpdateVO actual = new RoleDatatableProvider(roleDatatableDPO).updateResponse(id);

		assertNotNull(actual);
		assertThat(actual).usingRecursiveComparison().isEqualTo(dummyRoleUpdateVO);
	}

	@Test
	public void testUpdateRequest() throws WebUpdateDataException {
		RoleUpdateDTO dummyRoleUpdateDTO = new DummyRoleUpdateDTOFactory().make();

		Mockito.when(roleDatatableDPO.roleUpdate(dummyRoleUpdateDTO, id)).thenReturn(true);
		boolean actual = new RoleDatatableProvider(roleDatatableDPO).updateRequest(dummyRoleUpdateDTO, id);

		assertTrue(actual);
	}

	@Test
	public void testUpdateRequestWithException() throws WebUpdateDataException {

		RoleUpdateDTO dummyRoleUpdateDTO = new DummyRoleUpdateDTOFactory().make();

		Mockito.when(roleDatatableDPO.roleUpdate(dummyRoleUpdateDTO, id)).thenReturn(false);

		Exception exception = assertThrows(WebUpdateDataException.class, () -> {
			new RoleDatatableProvider(roleDatatableDPO).updateRequest(dummyRoleUpdateDTO, id);
		});

		String expectedMessage = "Unable to update: " + dummyRoleUpdateDTO.getName();
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

}
