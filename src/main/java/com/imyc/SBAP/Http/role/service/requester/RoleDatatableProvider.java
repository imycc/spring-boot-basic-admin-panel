package com.imyc.SBAP.Http.role.service.requester;

import com.imyc.SBAP.Http.role.service.dpl.RoleDatatableDPO;
import com.imyc.SBAP.Http.role.service.requester.contracts.RoleIndexRequester;
import com.imyc.SBAP.Http.role.viewobject.RoleDatatableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RoleDatatableProvider implements RoleIndexRequester {

    private RoleDatatableDPO roleDatatableDPO;

    @Autowired
    public RoleDatatableProvider(RoleDatatableDPO roleDatatableDPO) {
        this.roleDatatableDPO = roleDatatableDPO;
    }

    // Index

    @Override
    public RoleDatatableVO indexResponse(HashMap<String, Object> serverSideConfig) {
        RoleDatatableVO roleDatatableVO = roleDatatableDPO.getRoleDatatableVO(serverSideConfig);

        return roleDatatableVO;
    }
}
