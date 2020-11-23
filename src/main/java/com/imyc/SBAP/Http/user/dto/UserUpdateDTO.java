package com.imyc.SBAP.Http.user.dto;

import com.imyc.SBAP.Base.valid.columnUnqiue.annotation.UniqueEmail;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Setter
@Getter
@Accessors(chain=true)
public class UserUpdateDTO {
	
	@NotEmpty
	private String name;

	@NotEmpty
	private String username;

	@Email
	@UniqueEmail
	private String email;

	private String password;
	@NotEmpty
	private List<Integer> roles;
	
}
