package com.imyc.SBAP.Http.auth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.imyc.SBAP.Http.auth.response.AuthenticateResult;
import com.imyc.SBAP.Http.auth.viewobject.LoginVO;

public class TestAuthenticationHandler {
	
	private LoginVO loginVO;
	private AuthenticateResult authenticateResult;
	
	@Before
	public void before() {
		loginVO = new LoginVO();
		String username = "admin";
		String password = "admin";
		loginVO.setUsername(username);
		loginVO.setPassword(password);
		
		authenticateResult = new AuthenticateResult();
		authenticateResult.setStatus("OK");
		
	}

	@Test
	public void handleAuthenticate() {
		AuthenticationHandler authHandler = new AuthenticationHandler();

		AuthenticateResult checkResult = authHandler.handleAuthenticate(loginVO);

		assertEquals(authenticateResult.getStatus(), checkResult.getStatus());
	}

}
