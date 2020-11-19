package com.imyc.SBAP.Http.role.service.requester;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import com.imyc.SBAP.Http.role.service.dataprocess.RoleDatatableDPO;
import com.imyc.SBAP.Http.role.service.requester.contracts.RoleIndexRequester;
import com.imyc.SBAP.Http.role.viewobject.RoleDatatableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier(value="RoleDatatableProvider")
public class RoleDatatableProvider implements RoleIndexRequester {

    private RoleDatatableDPO roleDatatableDPO;

    @Autowired
    public RoleDatatableProvider(RoleDatatableDPO roleDatatableDPO) {
        this.roleDatatableDPO = roleDatatableDPO;
    }

    // Index

    @Override
    public RoleDatatableVO indexResponse(DatatableServerSideConfig datatableServerSideConfig) {
        RoleDatatableVO roleDatatableVO = roleDatatableDPO.getRoleDatatableVO(datatableServerSideConfig);

        return roleDatatableVO;
    }
}
