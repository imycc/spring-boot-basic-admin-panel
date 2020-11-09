package com.imyc.SBAP.Http.user.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain=true)
public class UserUpdateDTO {
	
	@NotEmpty
	private String name;
	@NotEmpty
	private String username;
	@Email
	private String email;
	private String password;
	@NotEmpty
	private List<Integer> roles;
	
}
