package com.imyc.SBAP.Http.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain=true)
public class UserCreateDTO {

	@NotEmpty
	private String name;
	@Email
	private String email;
	@NotEmpty
	private String password;
	private String password_confirmation;
	@NotEmpty
	private String[] roles[];
	
}
