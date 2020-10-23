package com.imyc.SBAP.Http.user.viewobject.datatable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain=true)
public class UserRow {

	private Integer id;
	private String name;
	private String email;
	private boolean disabled;
}
