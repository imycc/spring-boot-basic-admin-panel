package com.imyc.SBAP.Http.user.viewobject;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
public class UserReadVO {
	
	private Integer id;
	private String username;
	private String name;
	private String email;
	private boolean disabled;
	private Date createdAt;
	private Date updatedAt;
	private String[] roles;

}