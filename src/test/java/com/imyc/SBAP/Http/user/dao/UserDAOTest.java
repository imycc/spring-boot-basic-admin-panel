package com.imyc.SBAP.Http.user.dao;

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
import com.imyc.SBAP.Http.user.model.Users;
import com.imyc.SBAP.Http.user.repository.UserRepository;
import com.imyc.SBAP.Http.user.viewobject.UserVO;

public class UserDAOTest {

	@Mock
	private UserRepository userRepo;
	private Users user;
	private Roles adminRole;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new Users();
		adminRole = new Roles();
		adminRole
			.setAdmin(true)
			.setId(1)
			.setName("ADMIN");
	}
	
	@Test
	public void testGetByUsername() {
		String username = "admin";
		Set<Roles> roleSet = new HashSet<>();
		roleSet.add(adminRole);
		
		user
			.setUsername("admin")
			.setPassword("admin")
			.setRoles(roleSet);
		Optional<Users> dummyUser = Optional.ofNullable(user);
		
		Mockito.when(userRepo.findByUsername(ArgumentMatchers.any(String.class))).thenReturn(dummyUser);
		Optional<UserVO> resultUser = new UserDAO(userRepo).getUserByUsername(username);
		
		assertNotNull(resultUser);
	}
	
	@Test
	public void testGetByUsername__UserNotFound() {
		
		Mockito.when(userRepo.findByUsername(ArgumentMatchers.any(String.class))).thenReturn(Optional.empty());
		Optional<UserVO> resultUser = new UserDAO(userRepo).getUserByUsername("NOT_EXIST");
		
		assertEquals(Optional.empty(), resultUser);
	}

}
