package com.imyc.SBAP.Http.role.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain=true)
public class RoleVO {
	private Integer id;
	private String name;
	private boolean checked = false;
}
