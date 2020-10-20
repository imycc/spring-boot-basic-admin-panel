package com.imyc.SBAP.Http.auth.action.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.imyc.SBAP.Http.auth.response.AuthenticateResult;
import com.imyc.SBAP.Http.auth.service.AuthenticationHandler;
import com.imyc.SBAP.Http.auth.viewobject.LoginVO;

public class TestPostLogin {
	
	@Mock
	private AuthenticationHandler authenticationHandler;
	private AuthenticateResult authenticateResult;
	private Map<String, Object> fakeResponse;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test_postLogin_success() {

		String username = "admin";
		String password = "admin";
		
		fakeResponse = new HashMap<>();
		fakeResponse.put("status", "OK");
		
		authenticateResult = new AuthenticateResult();
		authenticateResult.setStatus("OK");
		
		Mockito.when(authenticationHandler.handleAuthenticate(ArgumentMatchers.any(LoginVO.class))).thenReturn(authenticateResult);
		
		Map<String, Object> response = new PostLogin(authenticationHandler).login(username, password);
		
		assertEquals("OK", response.get("status"));
	}
	
	
	
	
	
}
