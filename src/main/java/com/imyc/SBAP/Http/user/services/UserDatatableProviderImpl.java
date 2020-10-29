package com.imyc.SBAP.Http.user.services;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imyc.SBAP.Exception.web.WebDeleteDataException;
import com.imyc.SBAP.Exception.web.WebPageNotFoundException;
import com.imyc.SBAP.Http.user.services.dpl.UserDatatableDPL;
import com.imyc.SBAP.Http.user.viewobject.UserCreateVO;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import com.imyc.SBAP.Http.user.viewobject.UserReadVO;

@Service
public class UserDatatableProviderImpl implements UserDatatableProvider {

	private UserDatatableDPL userDatatableDAO;

	@Autowired
	public UserDatatableProviderImpl(UserDatatableDPL userDatatableDAO) {
		this.userDatatableDAO = userDatatableDAO;
	}

	@Override
	public UserDatatableVO loadAllUserForDatatable(HashMap<String, Object> serverSideConfig) {

		UserDatatableVO userDatatableVO = userDatatableDAO.getUserDatatableVO(serverSideConfig);

		return userDatatableVO;
	}

	@Override
	public UserReadVO loadUserForUserRead(int id) throws WebPageNotFoundException {

		Optional<UserReadVO> optionalUserReadVO = userDatatableDAO.getUserDetailForRead(id);

		if (optionalUserReadVO.isPresent()) {
			return optionalUserReadVO.get();
		} else {
			throw new WebPageNotFoundException();
		}
	}

	@Override
	public boolean deleteUser(int id) throws WebDeleteDataException {

		boolean isDeleted = userDatatableDAO.deleteUserWithRelationById(id);
		
		if (isDeleted) {
			return true;
		} else {
			throw new WebDeleteDataException("Unable to delete item: " + id);
		}
	}

	@Override
	public UserCreateVO loadRoleListForUserCreate() {

		UserCreateVO userCreateVO = userDatatableDAO.getRoleListForUserCreate();
		
		return userCreateVO;
	}

}
