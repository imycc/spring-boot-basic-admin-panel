package com.imyc.SBAP.Http.privilege.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain=true)
public class PrivilegeVO {

    private int id;
    private String name;
    private boolean checked = false;

}
