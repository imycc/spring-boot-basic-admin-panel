package com.imyc.SBAP.Http.role.action.api;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import com.imyc.SBAP.Http.role.service.requester.contracts.RoleIndexRequester;
import com.imyc.SBAP.Http.role.viewobject.RoleDatatableVO;
import com.imyc.SBAP.factories.dummy.base.DummyDatatableServerSideConfigFactory;
import com.imyc.SBAP.factories.dummy.role.DummyRoleDatatableVOFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetRoleDatatableTest {

	@Mock
	private RoleIndexRequester roleIndexRequester;
	private GetRoleDatatable getRoleDatatable;
	private DatatableServerSideConfig dummyDatatableServerSideConfig;
	private RoleDatatableVO dummyRoleDatatableVO;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		getRoleDatatable = new GetRoleDatatable(roleIndexRequester);
	}

	@Test
	public void testGetUserDatatableWithOutKeyword() {

		dummyRoleDatatableVO = new DummyRoleDatatableVOFactory().make();
		dummyDatatableServerSideConfig = new DummyDatatableServerSideConfigFactory(1, 0, 10, "").make();

		Mockito.when(roleIndexRequester.indexResponse(dummyDatatableServerSideConfig)).thenReturn(dummyRoleDatatableVO);
		ResponseEntity<RoleDatatableVO> actual = getRoleDatatable.handle(1, 0, 10 ,"");

		ResponseEntity<RoleDatatableVO> expected = new ResponseEntity<>(dummyRoleDatatableVO, HttpStatus.OK);
		
		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

}
