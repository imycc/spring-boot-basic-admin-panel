package com.imyc.SBAP.Http.user.viewobject;

import java.util.List;

import com.imyc.SBAP.Http.role.viewobject.RoleVO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain=true)
public class UserCreateVO {
	private List<RoleVO> roleVOList;
}
