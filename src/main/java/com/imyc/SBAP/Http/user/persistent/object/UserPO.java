package com.imyc.SBAP.Http.user.persistent.object;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.imyc.SBAP.Http.role.model.Roles;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
public class UserPO {
	
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

}
