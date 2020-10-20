package com.imyc.SBAP.Http.auth.service;

import org.springframework.stereotype.Service;

import com.imyc.SBAP.Http.auth.response.AuthenticateResult;
import com.imyc.SBAP.Http.auth.viewobject.LoginVO;

@Service
public class AuthenticationHandler {

	public AuthenticateResult handleAuthenticate(LoginVO loginVO) {
		AuthenticateResult authenticateResult = authenticateResult();
		
		String username = loginVO.getUsername();
		
		if (checkUserExist(username)) {
			authenticateResult.setStatus("OK");
		}else {
			authenticateResult.setStatus("Fail");
		}
		
		return authenticateResult;
	}
	
    private boolean checkUserExist(String username) {
    	
    	return "admin" == username;
    }
    
    private AuthenticateResult authenticateResult() {
    	return new AuthenticateResult();
    }
	
}
