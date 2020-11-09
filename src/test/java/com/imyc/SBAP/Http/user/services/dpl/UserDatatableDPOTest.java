package com.imyc.SBAP.Http.user.services.dpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.imyc.SBAP.Http.user.viewobject.UserUpdateVO;
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

import com.imyc.SBAP.Http.role.model.Roles;
import com.imyc.SBAP.Http.role.repo.RoleRepository;
import com.imyc.SBAP.Http.user.dao.Users;
import com.imyc.SBAP.Http.user.dao.repository.UserRepository;
import com.imyc.SBAP.Http.user.viewobject.UserCreateVO;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import com.imyc.SBAP.Http.user.viewobject.UserReadVO;
import com.imyc.SBAP.factories.dummy.role.DummyRoleFactory;
import com.imyc.SBAP.factories.dummy.user.DummyUserCreateVOFactory;
import com.imyc.SBAP.factories.dummy.user.DummyUserDatatableVOFactory;
import com.imyc.SBAP.factories.dummy.user.DummyUserFactory;

public class UserDatatableDPOTest {

	@Mock
	private UserRepository userRepo;
	@Mock
	private RoleRepository roleRepo;
	private HashMap<String, Object> serverSideConfig;
	private UserDatatableVO userDatatableVO;
	private List<Users> userList;
	private int id = 1;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		serverSideConfig = new HashMap<>();
		
		serverSideConfig.put("draw", 1);
		serverSideConfig.put("start", 0);
		serverSideConfig.put("length", 10);
		
		userList = new ArrayList<Users>();
		Users user = new DummyUserFactory("ADMIN").make();
		userList.add(user);
	}
	
	//Index

	@Test
	public void testGetUserDatatableVO() {
		int start = (int) serverSideConfig.get("start");
		int length = (int) serverSideConfig.get("length");
		
		userDatatableVO = new DummyUserDatatableVOFactory().make();
		
		PageRequest pagable = PageRequest.of(start, length);
		Page<Users> pageUserList = new PageImpl<>(userList, pagable, userList.size());
		
		Mockito.when(userRepo.findAll(ArgumentMatchers.<Specification<Users>>any(), ArgumentMatchers.<Pageable>any()))
				.thenReturn(pageUserList);
		UserDatatableVO actual = new UserDatatableDPO(userRepo, roleRepo).getUserDatatableVO(serverSideConfig);
		
		assertNotNull(actual);
		assertNotNull(actual.getData());
		assertEquals(userDatatableVO.getDraw(), actual.getDraw());
		assertEquals(userDatatableVO.getRecordsFiltered(), actual.getRecordsFiltered());
		assertEquals(userDatatableVO.getRecordsTotal(), actual.getRecordsTotal());
	}
	
	//Read
	
	@Test
	public void testGetUserDetailForRead() {
		int id = 1;
		Optional<Users> optionalUser = Optional.of(new DummyUserFactory("ADMIN").make());
		
		Mockito.when(userRepo.findById(ArgumentMatchers.any(Integer.class))).thenReturn(optionalUser);
		Optional<UserReadVO> actual = new UserDatatableDPO(userRepo, roleRepo).getUserDetailForRead(id);

		assertNotNull(actual.get());
	}
	
	@Test
	public void testGetUserDetailForReadIsEmpty() {
		int id = 1;
		
		Mockito.when(userRepo.findById(ArgumentMatchers.any(Integer.class))).thenReturn(Optional.empty());
		Optional<UserReadVO> actual = new UserDatatableDPO(userRepo, roleRepo).getUserDetailForRead(id);

		assertTrue(actual.isEmpty());
	}
	
	//Delete
	
	@Test
	public void testDeleteUserWithRelationById() {
		int id = 1;
		
		Mockito.when(userRepo.existsById(ArgumentMatchers.any(Integer.class))).thenReturn(true);
		boolean actual = new UserDatatableDPO(userRepo, roleRepo).deleteUserWithRelationById(id);

		assertTrue(actual);
	}

	@Test
	public void testDeleteUserWithRelationByIdIfUserIsNotExist() {
		int id = 1;
		
		Mockito.when(userRepo.existsById(ArgumentMatchers.any(Integer.class))).thenReturn(false);
		boolean actual = new UserDatatableDPO(userRepo, roleRepo).deleteUserWithRelationById(id);

		assertFalse(actual);
	}
	
	// Create
	
	@Test
	public void testGetRoleListForUserCreate() {
		
		UserCreateVO dummyUserCreateVO = new DummyUserCreateVOFactory().make();
		
		List<Roles> dummyUserRoleList = new ArrayList<Roles>();
		dummyUserRoleList.add(new DummyRoleFactory(1, "ADMIN").make());
		dummyUserRoleList.add(new DummyRoleFactory(2, "USER").make());
		
		Mockito.when(roleRepo.findAll()).thenReturn(dummyUserRoleList);
		UserCreateVO actual = new UserDatatableDPO(userRepo, roleRepo).getRoleListForUserCreate();

		assertNotNull(actual.getRoleVOList());
        assertThat(actual).usingRecursiveComparison().isEqualTo(dummyUserCreateVO);
	}

	// Update

	@Test
	public void testGetUserForUserUpdate() {
		UserUpdateVO dummyUserUpdateVO = new DummyUserUpdateVOFactory().make();
		Users dummyUsers = new DummyUserFactory("Admin").make();

		List<Roles> dummyUserRoleList = new ArrayList<Roles>();
		dummyUserRoleList.add(new DummyRoleFactory(2, "USER").make());

		Mockito.when(userRepo.findById(id)).thenReturn(Optional.of(dummyUsers));
		Mockito.when(roleRepo.findAll()).thenReturn(dummyUserRoleList);
		Optional<UserUpdateVO> actual = new UserDatatableDPO(userRepo, roleRepo).getUserForUserUpdate(id);

		assertNotNull(actual.get());
	}
}
