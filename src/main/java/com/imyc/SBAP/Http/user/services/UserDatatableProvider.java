package com.imyc.SBAP.Http.user.services;

import java.util.HashMap;
import com.imyc.SBAP.Http.user.viewobject.UserCreateVO;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import com.imyc.SBAP.Http.user.viewobject.UserReadVO;
import com.imyc.SBAP.Exception.web.WebDeleteDataException;
import com.imyc.SBAP.Exception.web.WebPageNotFoundException;

public interface UserDatatableProvider {

	UserDatatableVO loadAllUserForDatatable(HashMap<String, Object> serverSideConfig);

	UserReadVO loadUserForUserRead(int id) throws WebPageNotFoundException;

	boolean deleteUser(int id) throws WebDeleteDataException;

	UserCreateVO loadRoleListForUserCreate();

}
