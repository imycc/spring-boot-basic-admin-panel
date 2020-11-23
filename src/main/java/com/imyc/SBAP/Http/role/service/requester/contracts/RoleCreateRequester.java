package com.imyc.SBAP.Http.role.service.requester.contracts;

import com.imyc.SBAP.Base.services.CRUDContracts.CreateContract;
import com.imyc.SBAP.Http.role.dto.RoleCreateDTO;
import com.imyc.SBAP.Http.role.viewobject.RoleCreateVO;

public interface RoleCreateRequester extends CreateContract<RoleCreateVO, RoleCreateDTO> {
}
