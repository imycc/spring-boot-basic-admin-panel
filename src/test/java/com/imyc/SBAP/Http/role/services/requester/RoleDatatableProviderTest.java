package com.imyc.SBAP.Http.role.services.requester;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import com.imyc.SBAP.Exception.web.WebCreateDataException;
import com.imyc.SBAP.Http.role.dto.RoleCreateDTO;
import com.imyc.SBAP.Http.role.service.dataprocess.RoleDatatableDPO;
import com.imyc.SBAP.Http.role.service.requester.RoleDatatableProvider;
import com.imyc.SBAP.Http.role.viewobject.RoleCreateVO;
import com.imyc.SBAP.Http.role.viewobject.RoleDatatableVO;
import com.imyc.SBAP.factories.dummy.base.DummyDatatableServerSideConfigFactory;
import com.imyc.SBAP.factories.dummy.role.DummyRoleCreateDTOFactory;
import com.imyc.SBAP.factories.dummy.role.DummyRoleCreateVOFactory;
import com.imyc.SBAP.factories.dummy.role.DummyRoleDatatableVOFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
	public void testGetAllUserForDatatable() {

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

}
