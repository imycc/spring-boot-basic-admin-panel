package com.imyc.SBAP.Http.role.viewobject.datatable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain=true)
public class RoleRow {
    private Integer id;
    private String name;
}
