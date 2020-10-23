package com.imyc.SBAP.Http.user.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain=true)
public class UserDatatableVO {

	private Integer id;
	private String username;
	private String name;
	private String email;
	private boolean disabled;
	private boolean accountExpired;
	
}
