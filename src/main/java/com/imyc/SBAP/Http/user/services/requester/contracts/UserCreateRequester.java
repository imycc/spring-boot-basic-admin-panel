package com.imyc.SBAP.Http.user.services.requester.contracts;

import com.imyc.SBAP.Base.services.CRUDContracts.CreateContract;
import com.imyc.SBAP.Http.user.dto.UserCreateDTO;
import com.imyc.SBAP.Http.user.viewobject.UserCreateVO;

public interface UserCreateRequester extends CreateContract<UserCreateVO, UserCreateDTO> {
	
}
