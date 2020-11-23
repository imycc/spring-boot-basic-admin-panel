package com.imyc.SBAP.Http.role.service.requester;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import com.imyc.SBAP.Exception.web.WebCreateDataException;
import com.imyc.SBAP.Http.role.dto.RoleCreateDTO;
import com.imyc.SBAP.Http.role.service.dataprocess.RoleDatatableDPO;
import com.imyc.SBAP.Http.role.service.requester.contracts.RoleCreateRequester;
import com.imyc.SBAP.Http.role.service.requester.contracts.RoleIndexRequester;
import com.imyc.SBAP.Http.role.viewobject.RoleCreateVO;
import com.imyc.SBAP.Http.role.viewobject.RoleDatatableVO;
import com.imyc.SBAP.Http.user.viewobject.UserCreateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier(value="RoleDatatableProvider")
public class RoleDatatableProvider implements RoleIndexRequester, RoleCreateRequester {

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

    // Create
    @Override
    public RoleCreateVO createResponse() {
        RoleCreateVO roleCreateVO = roleDatatableDPO.getPrivilegeList();

        return roleCreateVO;
    }

    @Override
    public boolean createRequest(RoleCreateDTO roleCreateDTO) throws WebCreateDataException {
        boolean isCreated = roleDatatableDPO.roleCreate(roleCreateDTO);

        if (isCreated) {
            return true;
        } else {
            throw new WebCreateDataException("Unable to create: " + roleCreateDTO.getName());
        }
    }

    // Update

    // Delete
}
