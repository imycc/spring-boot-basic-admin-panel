package com.imyc.SBAP.Http.user.services.requester;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.imyc.SBAP.Http.user.services.UserDetailsServiceImpl;
import com.imyc.SBAP.factories.dummy.user.DummyUserVOFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.imyc.SBAP.Http.user.services.dataprocess.UserDPO;
import com.imyc.SBAP.Http.user.viewobject.UserVO;

public class UserDetailsServiceImplTest {
	
	@Mock
	private UserDPO userDAO;
	
	private List<String> roleList;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		roleList = new ArrayList<String>();
	}
	
	@Test
	public void expectUserFoundByLoadUserByUsername() {

		String username = "admin";
		roleList.add("ADMIN");
		
		UserVO dummyUserVO = new DummyUserVOFactory().make();
		Optional<UserVO> dummyUserPO = Optional.ofNullable(dummyUserVO);
		
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
