package com.imyc.SBAP.Http.auth.action.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imyc.SBAP.Http.auth.response.AuthenticateResult;
import com.imyc.SBAP.Http.auth.service.AuthenticationHandler;
import com.imyc.SBAP.Http.auth.viewobject.LoginVO;

@RestController
public class PostLogin {

	@Autowired
	AuthenticationHandler authenticationHandler;

	@PostMapping("api/login")
	public Map<String, Object> login(@RequestParam String username, @RequestParam String password) {

		LoginVO loginVO = new LoginVO();
		loginVO.setUsername(username);
		loginVO.setPassword(password);

		AuthenticateResult authenticateResult = authenticationHandler.handleAuthenticate(loginVO);

		Map<String, Object> json = new HashMap<>();
		json.put("status", authenticateResult.getStatus());
		
		return json;
	}
}
