package com.imyc.SBAP.Http.role.service.requester;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import com.imyc.SBAP.Exception.web.WebCreateDataException;
import com.imyc.SBAP.Exception.web.WebDeleteDataException;
import com.imyc.SBAP.Exception.web.WebPageNotFoundException;
import com.imyc.SBAP.Exception.web.WebUpdateDataException;
import com.imyc.SBAP.Http.role.dto.RoleCreateDTO;
import com.imyc.SBAP.Http.role.dto.RoleUpdateDTO;
import com.imyc.SBAP.Http.role.service.dataprocess.RoleDatatableDPO;
import com.imyc.SBAP.Http.role.service.requester.contracts.RoleCreateRequester;
import com.imyc.SBAP.Http.role.service.requester.contracts.RoleDeleteRequester;
import com.imyc.SBAP.Http.role.service.requester.contracts.RoleIndexRequester;
import com.imyc.SBAP.Http.role.service.requester.contracts.RoleUpdateRequester;
import com.imyc.SBAP.Http.role.viewobject.RoleCreateVO;
import com.imyc.SBAP.Http.role.viewobject.RoleDatatableVO;
import com.imyc.SBAP.Http.role.viewobject.RoleUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier(value="RoleDatatableProvider")
public class RoleDatatableProvider implements RoleIndexRequester, RoleCreateRequester, RoleDeleteRequester, RoleUpdateRequester {

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

    // Delete
    @Override
    public boolean deleteRequest(int id) throws WebDeleteDataException {
        boolean isDeleted = roleDatatableDPO.deleteRoleWithRelationById(id);

        if (isDeleted) {
            return true;
        } else {
            throw new WebDeleteDataException("Unable to delete: " + id);
        }
    }

    // Update

    @Override
    public RoleUpdateVO updateResponse(int id) throws WebPageNotFoundException {
        Optional<RoleUpdateVO> optionalRoleUpdateVO = roleDatatableDPO.getRoleForUpdate(id);

        if (optionalRoleUpdateVO.isPresent()) {
            return optionalRoleUpdateVO.get();
        } else {
            throw new WebPageNotFoundException();
        }
    }

    @Override
    public boolean updateRequest(RoleUpdateDTO roleUpdateDTO, int id) throws WebUpdateDataException {
        boolean isUpdated = roleDatatableDPO.roleUpdate(roleUpdateDTO, id);

        if (isUpdated) {
            return true;
        } else {
            throw new WebUpdateDataException("Unable to update: " + roleUpdateDTO.getName());
        }
    }


}
