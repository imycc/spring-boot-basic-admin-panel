package com.imyc.SBAP.Http.user.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.imyc.SBAP.Http.user.dao.UserDAO;
import com.imyc.SBAP.Http.user.dao.UserRepository;

import com.imyc.SBAP.Http.user.persistent.object.UserPO;

class UserDetailsServiceImplTest {
	
	@Mock
	private UserRepository userRepo;
	@Mock
	private UserDAO userDAO;
	private UserDetailsService userDetailsService;
	private UserPO userPO;
	private List<String> roleList;
	
	@Before
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void TestloadUserByUsername() {

		String username = "admin";
		userDAO = new UserDAO(userRepo);
		userDetailsService = new UserDetailsServiceImpl(userDAO, userRepo);
		roleList = new ArrayList<String>();
		roleList.add("ADMIN");
		
		userPO = new UserPO();
		userPO
			.setUsername("admin")
			.setPassword("admin")
			.setRoles(roleList.toArray(new String[0]));
		Optional<UserPO> resultPO = Optional.ofNullable(userPO);
		
		Mockito.when(userDAO.getByUsername(ArgumentMatchers.any(String.class))).thenReturn(resultPO);
		UserDetails userDetail = userDetailsService.loadUserByUsername(username);
		
		assertEquals(userDetail, userDetail);
	}

}
