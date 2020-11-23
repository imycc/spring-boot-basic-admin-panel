package com.imyc.SBAP.Http.role.viewobject;

import com.imyc.SBAP.Http.privilege.viewobject.PrivilegeVO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain=true)
public class RoleUpdateVO {

    private String name;
    private Boolean isAdmin;
    private List<PrivilegeVO> privilegeList;
}
