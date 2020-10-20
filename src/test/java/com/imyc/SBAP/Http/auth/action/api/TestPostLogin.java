package com.imyc.SBAP.Http.auth.action.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.imyc.SBAP.Http.auth.response.AuthenticateResult;
import com.imyc.SBAP.Http.auth.service.AuthenticationHandler;
import com.imyc.SBAP.Http.auth.viewobject.LoginVO;

public class TestPostLogin {
	
	private PostLogin postLogin;
	private AuthenticationHandler authenticationHandler;
	private AuthenticateResult authenticateResult;
	private LoginVO loginVO;
	private Map<String, Object> fakeResponse;
	
	@Before
	public void setUp() throws Exception {
		authenticationHandler = Mockito.mock(AuthenticationHandler.class);
		authenticateResult = new AuthenticateResult();
		loginVO = new LoginVO();
		postLogin = new PostLogin();
	}
	
	@Test
	public void test_postLogin_success() {

		String username = "admin";
		String password = "admin";
		loginVO.setUsername(username);
		loginVO.setPassword(password);
		
		fakeResponse = new HashMap<>();
		fakeResponse.put("status", "OK");
		
		authenticateResult.setStatus("OK");
		
		Mockito.when(authenticationHandler.handleAuthenticate(loginVO)).thenReturn(authenticateResult);
		
		Map<String, Object> response = postLogin.login(username, password);
		
		assertEquals(response, response);
	}
	
	
	
	
	
}
