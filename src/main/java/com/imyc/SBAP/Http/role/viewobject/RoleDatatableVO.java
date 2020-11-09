package com.imyc.SBAP.Http.role.viewobject;

import com.imyc.SBAP.Http.role.viewobject.datatable.RoleRow;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain=true)
public class RoleDatatableVO {

    private Integer draw;
    private Long recordsTotal;
    private Long recordsFiltered;
    private List<RoleRow> data;
}
