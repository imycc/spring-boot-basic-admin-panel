package com.imyc.SBAP.Http.user.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.imyc.SBAP.Http.user.dao.UserDAO;
import com.imyc.SBAP.Http.user.persistent.object.UserPO;

public class UserDetailsServiceImplTest {
	
	@Mock
	private UserDAO userDAO;
	
	private UserPO userPO;
	private List<String> roleList;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		roleList = new ArrayList<String>();
		userPO = new UserPO();
	}
	
	@Test
	public void TestloadUserByUsername() {

		String username = "admin";
		roleList.add("ADMIN");
		
		userPO
			.setUsername("admin")
			.setPassword("admin")
			.setRoles(roleList.toArray(new String[0]));
		Optional<UserPO> resultPO = Optional.ofNullable(userPO);
		
		Mockito.when(userDAO.getByUsername(ArgumentMatchers.any(String.class))).thenReturn(resultPO);
		UserDetails userDetail = new UserDetailsServiceImpl(userDAO).loadUserByUsername(username);
		
		assertNotNull(userDetail);
		assertTrue(userDetail.isAccountNonLocked());
	}
	
	@Test
    public void TestloadUserByUsername_UserNotFound() {
		UserDetailsServiceImpl u= new UserDetailsServiceImpl(userDAO);
		
	 	Mockito.when(userDAO.getByUsername(ArgumentMatchers.any(String.class))).thenReturn(Optional.empty());
	 	assertThrows(UsernameNotFoundException.class, () -> {
	 			u.loadUserByUsername("not_exist");
	 		}
		);

    }

}