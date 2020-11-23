package com.imyc.SBAP.Http.user.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.imyc.SBAP.Base.valid.columnUnqiue.annotation.UniqueEmail;
import com.imyc.SBAP.Base.valid.combinedNotNull.annotation.CombinedNotNull;
import com.imyc.SBAP.Base.valid.passwordValid.annotation.PasswordValueMatch;
import com.imyc.SBAP.Base.valid.passwordValid.annotation.ValidPassword;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@PasswordValueMatch.List({
		@PasswordValueMatch(
				field = "password",
				fieldMatch = "confirmPassword",
				message = "{dto.password.confirm}"
		)
})
@Setter
@Getter
@Accessors(chain=true)
public class UserCreateDTO {

	@NotEmpty
	private String name;
	@NotEmpty
	private String username;

	@Email
	@UniqueEmail
	private String email;

	@NotEmpty
	@ValidPassword
	private String password;

	@NotEmpty
	@ValidPassword
	private List<Integer> roles;
	
}
