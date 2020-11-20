package com.imyc.SBAP.Http.user.viewobject;

import java.util.Date;
import java.util.List;

import com.imyc.SBAP.Http.role.dao.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
public class UserVO {
	
	private Integer id;
	private String username;
	private String name;
	private String email;
	private String password;
	private boolean disabled;
	private boolean accountExpired;
	private boolean accountLocked;
	private boolean credentialsExpired;
	private Date createdAt;
	private Date updatedAt;
	private String[] roles;
	private List<String> privilege;

}
