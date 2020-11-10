package com.imyc.SBAP.Http.role.services.requester;

import com.imyc.SBAP.Exception.web.WebCreateDataException;
import com.imyc.SBAP.Exception.web.WebDeleteDataException;
import com.imyc.SBAP.Exception.web.WebPageNotFoundException;
import com.imyc.SBAP.Exception.web.WebUpdateDataException;
import com.imyc.SBAP.Http.role.service.dpl.RoleDatatableDPO;
import com.imyc.SBAP.Http.role.service.requester.RoleDatatableProvider;
import com.imyc.SBAP.Http.role.viewobject.RoleDatatableVO;
import com.imyc.SBAP.Http.user.dto.UserCreateDTO;
import com.imyc.SBAP.Http.user.dto.UserUpdateDTO;
import com.imyc.SBAP.Http.user.services.requester.UserDatatableProvider;
import com.imyc.SBAP.Http.user.viewobject.UserCreateVO;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import com.imyc.SBAP.Http.user.viewobject.UserReadVO;
import com.imyc.SBAP.Http.user.viewobject.UserUpdateVO;
import com.imyc.SBAP.factories.dummy.role.DummyRoleDatatableVOFactory;
import com.imyc.SBAP.factories.dummy.user.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class RoleDatatableProviderTest {

	@Mock
	private RoleDatatableDPO roleDatatableDPO;
	private HashMap<String, Object> serverSideConfig;
	private RoleDatatableVO dummyRoleDatatableVO;
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

		dummyRoleDatatableVO = new DummyRoleDatatableVOFactory().make();
		
		Mockito.when(roleDatatableDPO.getRoleDatatableVO(serverSideConfig)).thenReturn(dummyRoleDatatableVO);
		RoleDatatableVO actual = new RoleDatatableProvider(roleDatatableDPO).indexResponse(serverSideConfig);
		
		assertNotNull(actual);
		assertEquals(dummyRoleDatatableVO, actual);
	}

}
