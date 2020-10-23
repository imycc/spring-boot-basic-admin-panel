package com.imyc.SBAP.Http.user.viewobject;

import java.util.List;

import com.imyc.SBAP.Http.user.viewobject.datatable.UserRow;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain=true)
public class UserDatatableVO {

	private Integer draw;
	private Integer recordsTotal;
	private Integer recordsFiltered;
	private List<UserRow> data;
	
}
