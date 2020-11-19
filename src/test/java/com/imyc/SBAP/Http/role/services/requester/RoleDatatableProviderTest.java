package com.imyc.SBAP.Http.role.services.requester;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import com.imyc.SBAP.Http.role.service.dataprocess.RoleDatatableDPO;
import com.imyc.SBAP.Http.role.service.requester.RoleDatatableProvider;
import com.imyc.SBAP.Http.role.viewobject.RoleDatatableVO;
import com.imyc.SBAP.factories.dummy.base.DummyDatatableServerSideConfigFactory;
import com.imyc.SBAP.factories.dummy.role.DummyRoleDatatableVOFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

}
