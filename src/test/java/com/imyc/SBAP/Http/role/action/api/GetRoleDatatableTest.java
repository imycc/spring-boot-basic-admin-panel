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
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class GetRoleDatatableTest {

	@Mock
	private RoleIndexRequester roleIndexContract;
	private GetRoleDatatable getRoleDatatable;
	private RoleDatatableVO dummyRoleDatatableVO;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		getRoleDatatable = new GetRoleDatatable(roleIndexContract);
	}

	@Test
	public void testGetUserDatatableWithOutKeyword() {

		dummyRoleDatatableVO = new DummyRoleDatatableVOFactory().make();

		Mockito.when(roleIndexContract.indexResponse(any(DatatableServerSideConfig.class))).thenReturn(dummyRoleDatatableVO);
		ResponseEntity<RoleDatatableVO> actual = getRoleDatatable.handle(1, 0, 10 ,"");

		ResponseEntity<RoleDatatableVO> expected = new ResponseEntity<>(dummyRoleDatatableVO, HttpStatus.OK);

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

}
