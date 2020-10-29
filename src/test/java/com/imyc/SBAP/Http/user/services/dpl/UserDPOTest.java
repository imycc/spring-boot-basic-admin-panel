package com.imyc.SBAP.Http.user.services.dpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.imyc.SBAP.Http.role.model.Roles;
import com.imyc.SBAP.Http.user.dao.model.Users;
import com.imyc.SBAP.Http.user.dao.repository.UserRepository;
import com.imyc.SBAP.Http.user.viewobject.UserVO;
import com.imyc.SBAP.factories.dummy.role.DummyRoleFactory;

public class UserDPOTest {

	@Mock
	private UserRepository userRepo;
	private Users users;
	private Roles dummyAdminRole;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		users = new Users();
		dummyAdminRole = new DummyRoleFactory("ADMIN").make();
	}
	
	@Test
	public void testGetByUsername() {
		String username = "admin";
		Set<Roles> dummyRoleSet = new HashSet<>();
		dummyRoleSet.add(dummyAdminRole);
		
		users
			.setUsername("admin")
			.setPassword("admin")
			.setRoles(dummyRoleSet);
		Optional<Users> dummyUser = Optional.ofNullable(users);
		
		Mockito.when(userRepo.findByUsername(ArgumentMatchers.any(String.class))).thenReturn(dummyUser);
		Optional<UserVO> resultUser = new UserDPO(userRepo).getUserByUsername(username);
		
		assertNotNull(resultUser);
	}
	
	@Test
	public void testGetByUsernameIfUserNotFound() {
		
		Mockito.when(userRepo.findByUsername(ArgumentMatchers.any(String.class))).thenReturn(Optional.empty());
		Optional<UserVO> resultUser = new UserDPO(userRepo).getUserByUsername("NOT_EXIST");
		
		assertEquals(Optional.empty(), resultUser);
	}

	
	
}
