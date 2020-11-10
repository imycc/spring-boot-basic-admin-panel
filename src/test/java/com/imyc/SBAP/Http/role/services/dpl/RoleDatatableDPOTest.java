package com.imyc.SBAP.Http.role.services.dpl;

import com.imyc.SBAP.Http.role.dao.Roles;
import com.imyc.SBAP.Http.role.dao.repository.RoleRepository;
import com.imyc.SBAP.Http.role.service.dpl.RoleDatatableDPO;
import com.imyc.SBAP.Http.role.viewobject.RoleDatatableVO;
import com.imyc.SBAP.Http.user.dao.Users;
import com.imyc.SBAP.Http.user.dao.repository.UserRepository;
import com.imyc.SBAP.Http.user.services.dpl.UserDatatableDPO;
import com.imyc.SBAP.Http.user.viewobject.UserCreateVO;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import com.imyc.SBAP.Http.user.viewobject.UserReadVO;
import com.imyc.SBAP.Http.user.viewobject.UserUpdateVO;
import com.imyc.SBAP.factories.dummy.role.DummyRoleDatatableVOFactory;
import com.imyc.SBAP.factories.dummy.role.DummyRoleFactory;
import com.imyc.SBAP.factories.dummy.user.DummyUserCreateVOFactory;
import com.imyc.SBAP.factories.dummy.user.DummyUserDatatableVOFactory;
import com.imyc.SBAP.factories.dummy.user.DummyUserFactory;
import com.imyc.SBAP.factories.dummy.user.DummyUserUpdateVOFactory;
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
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class RoleDatatableDPOTest {

	@Mock
	private RoleRepository roleRepo;
	private HashMap<String, Object> serverSideConfig;
	private RoleDatatableVO roleDatatableVO;
	private List<Roles> roleList;
	private int id = 1;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		serverSideConfig = new HashMap<>();
		
		serverSideConfig.put("draw", 1);
		serverSideConfig.put("start", 0);
		serverSideConfig.put("length", 10);

		roleList = new ArrayList<>();
		Roles role = new DummyRoleFactory(2, "ADMIN").make();
		roleList.add(role);
	}
	
	//Index

	@Test
	public void testGetUserDatatableVO() {
		int start = (int) serverSideConfig.get("start");
		int length = (int) serverSideConfig.get("length");

		roleDatatableVO = new DummyRoleDatatableVOFactory().make();
		
		PageRequest pagable = PageRequest.of(start, length);
		Page<Roles> pageRoleList = new PageImpl<>(roleList, pagable, roleList.size());
		
		Mockito.when(roleRepo.findAll(ArgumentMatchers.<Specification<Roles>>any(), ArgumentMatchers.<Pageable>any()))
				.thenReturn(pageRoleList);
		RoleDatatableVO actual = new RoleDatatableDPO(roleRepo).getRoleDatatableVO(serverSideConfig);
		
		assertNotNull(actual);
		assertNotNull(actual.getData());
		assertEquals(roleDatatableVO.getDraw(), actual.getDraw());
		assertEquals(roleDatatableVO.getRecordsFiltered(), actual.getRecordsFiltered());
		assertEquals(roleDatatableVO.getRecordsTotal(), actual.getRecordsTotal());
	}
	

}
