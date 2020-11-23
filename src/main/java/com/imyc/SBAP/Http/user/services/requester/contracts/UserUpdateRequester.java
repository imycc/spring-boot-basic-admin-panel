package com.imyc.SBAP.Http.user.services.requester.contracts;

import com.imyc.SBAP.Base.services.CRUDContracts.UpdateContract;
import com.imyc.SBAP.Http.user.dto.UserUpdateDTO;
import com.imyc.SBAP.Http.user.viewobject.UserUpdateVO;

public interface UserUpdateRequester extends UpdateContract<UserUpdateVO, UserUpdateDTO> {

}
