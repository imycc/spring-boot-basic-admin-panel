package com.imyc.SBAP.Http.role.services.dataprocess;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import com.imyc.SBAP.Http.role.dao.Role;
import com.imyc.SBAP.Http.role.dao.repository.RoleRepository;
import com.imyc.SBAP.Http.role.service.dataprocess.RoleDatatableDPO;
import com.imyc.SBAP.Http.role.viewobject.RoleDatatableVO;
import com.imyc.SBAP.factories.dummy.base.DummyDatatableServerSideConfigFactory;
import com.imyc.SBAP.factories.dummy.role.DummyRoleDatatableVOFactory;
import com.imyc.SBAP.factories.dummy.role.DummyRoleFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoleDatatableDPOTest {

	@Mock
	private RoleRepository roleRepo;
	private DatatableServerSideConfig dummyDatatableServerSideConfig;
	private RoleDatatableVO roleDatatableVO;
	private List<Role> roleList;
	private int id = 1;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		roleList = new ArrayList<>();
		Role role = new DummyRoleFactory(2, "ADMIN").make();
		roleList.add(role);
	}
	
	//Index

	@Test
	public void testGetUserDatatableVO() {

		dummyDatatableServerSideConfig = new DummyDatatableServerSideConfigFactory(1, 0, 10, null).make();
		roleDatatableVO = new DummyRoleDatatableVOFactory().make();
		
		PageRequest pagable = PageRequest.of(dummyDatatableServerSideConfig.getStart(), dummyDatatableServerSideConfig.getLength());
		Page<Role> pageRoleList = new PageImpl<>(roleList, pagable, roleList.size());
		
		Mockito.when(roleRepo.findAll(ArgumentMatchers.<Specification<Role>>any(), ArgumentMatchers.<Pageable>any()))
				.thenReturn(pageRoleList);
		RoleDatatableVO actual = new RoleDatatableDPO(roleRepo).getRoleDatatableVO(dummyDatatableServerSideConfig);
		
		assertNotNull(actual);
		assertNotNull(actual.getData());
		assertEquals(roleDatatableVO.getDraw(), actual.getDraw());
		assertEquals(roleDatatableVO.getRecordsFiltered(), actual.getRecordsFiltered());
		assertEquals(roleDatatableVO.getRecordsTotal(), actual.getRecordsTotal());
	}

}
