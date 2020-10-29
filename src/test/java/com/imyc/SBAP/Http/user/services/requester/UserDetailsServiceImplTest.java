package com.imyc.SBAP.Http.user.services.requester;

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

import com.imyc.SBAP.Http.user.services.Requester.UserDetailsServiceImpl;
import com.imyc.SBAP.Http.user.services.dpl.UserDPO;
import com.imyc.SBAP.Http.user.viewobject.UserVO;

public class UserDetailsServiceImplTest {
	
	@Mock
	private UserDPO userDAO;
	
	private UserVO userVO;
	private List<String> roleList;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		roleList = new ArrayList<String>();
		userVO = new UserVO();
	}
	
	@Test
	public void expectUserFoundByLoadUserByUsername() {

		String username = "admin";
		roleList.add("ADMIN");
		
		userVO
			.setUsername("admin")
			.setPassword("admin")
			.setRoles(roleList.toArray(new String[0]));
		Optional<UserVO> dummyUserPO = Optional.ofNullable(userVO);
		
		Mockito.when(userDAO.getUserByUsername(ArgumentMatchers.any(String.class))).thenReturn(dummyUserPO);
		UserDetails userDetail = new UserDetailsServiceImpl(userDAO).loadUserByUsername(username);
		
		assertNotNull(userDetail);
		assertTrue(userDetail.isAccountNonLocked());
	}
	
	@Test
    public void expectUserNotFoundByLoadUserByUsername() {
		UserDetailsServiceImpl u = new UserDetailsServiceImpl(userDAO);
		
	 	Mockito.when(userDAO.getUserByUsername(ArgumentMatchers.any(String.class))).thenReturn(Optional.empty());
	 	assertThrows(UsernameNotFoundException.class, () -> {
	 			u.loadUserByUsername("not_exist");
	 		}
		);

    }

}
