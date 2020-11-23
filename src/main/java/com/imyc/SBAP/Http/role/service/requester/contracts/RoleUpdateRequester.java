package com.imyc.SBAP.Http.role.service.requester.contracts;

import com.imyc.SBAP.Base.services.CRUDContracts.UpdateContract;
import com.imyc.SBAP.Http.role.dto.RoleUpdateDTO;
import com.imyc.SBAP.Http.role.viewobject.RoleUpdateVO;

public interface RoleUpdateRequester extends UpdateContract<RoleUpdateVO, RoleUpdateDTO> {
}
