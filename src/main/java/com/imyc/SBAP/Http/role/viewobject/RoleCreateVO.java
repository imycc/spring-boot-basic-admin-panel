package com.imyc.SBAP.Http.role.viewobject;

import com.imyc.SBAP.Http.privilege.viewobject.PrivilegeVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RoleCreateVO {

    private List<PrivilegeVO> privilegeList;

}
