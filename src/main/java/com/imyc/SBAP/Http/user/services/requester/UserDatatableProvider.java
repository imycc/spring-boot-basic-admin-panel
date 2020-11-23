package com.imyc.SBAP.Http.user.services.requester;

import java.util.Optional;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import com.imyc.SBAP.Exception.web.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.imyc.SBAP.Http.user.dto.UserCreateDTO;
import com.imyc.SBAP.Http.user.dto.UserUpdateDTO;
import com.imyc.SBAP.Http.user.services.dataprocess.UserDatatableDPO;
import com.imyc.SBAP.Http.user.services.requester.contracts.UserCreateRequester;
import com.imyc.SBAP.Http.user.services.requester.contracts.UserDeleteRequester;
import com.imyc.SBAP.Http.user.services.requester.contracts.UserIndexRequester;
import com.imyc.SBAP.Http.user.services.requester.contracts.UserReadRequester;
import com.imyc.SBAP.Http.user.services.requester.contracts.UserUpdateRequester;
import com.imyc.SBAP.Http.user.viewobject.UserCreateVO;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import com.imyc.SBAP.Http.user.viewobject.UserReadVO;
import com.imyc.SBAP.Http.user.viewobject.UserUpdateVO;

@Service
@Qualifier(value="UserDatatableProvider")
public class UserDatatableProvider implements UserCreateRequester, UserDeleteRequester, 
												UserIndexRequester, UserReadRequester, UserUpdateRequester {

	private UserDatatableDPO userDatatableDPO;

	@Autowired
	public UserDatatableProvider(UserDatatableDPO userDatatableDPO) {
		this.userDatatableDPO = userDatatableDPO;
	}

	// Index
	
	@Override
	public UserDatatableVO indexResponse(DatatableServerSideConfig datatableServerSideConfig) {

		UserDatatableVO userDatatableVO = userDatatableDPO.getUserDatatableVO(datatableServerSideConfig);

		return userDatatableVO;
	}

	// Read
	
	@Override
	public UserReadVO readResponse(int id) throws WebPageNotFoundException {

		Optional<UserReadVO> optionalUserReadVO = userDatatableDPO.getUserDetailForRead(id);

		if (optionalUserReadVO.isPresent()) {
			return optionalUserReadVO.get();
		} else {
			throw new WebPageNotFoundException();
		}
	}

	// Delete
	
	@Override
	public boolean deleteRequest(int id) throws WebDeleteDataException {

		boolean isDeleted = userDatatableDPO.deleteUserWithRelationById(id);
		
		if (isDeleted) {
			return true;
		} else {
			throw new WebDeleteDataException("Unable to delete: " + id);
		}
	}

	// Create
	
	@Override
	public UserCreateVO createResponse() {

		UserCreateVO userCreateVO = userDatatableDPO.getRoleListForUserCreate();
		
		return userCreateVO;
	}

	@Override
	public boolean createRequest(UserCreateDTO userCreateDTO) throws WebCreateDataException{
		
		boolean isCreated = userDatatableDPO.userCreate(userCreateDTO);
		
		if (isCreated) {
			return true;
		} else {
			throw new WebCreateDataException("Unable to create: " + userCreateDTO.getName());
		}
		
	}

	// Update
	
	@Override
	public UserUpdateVO updateResponse(int id) throws WebPageNotFoundException {

		Optional<UserUpdateVO> optionalUserUpdateVO = userDatatableDPO.getUserForUpdate(id);

		if (optionalUserUpdateVO.isPresent()) {
			return optionalUserUpdateVO.get();
		} else {
			throw new WebPageNotFoundException();
		}
	}

	@Override
	public boolean updateRequest(UserUpdateDTO userUpdateDTO, int id) throws WebUpdateDataException {
		boolean isUpdated = userDatatableDPO.userUpdate(userUpdateDTO, id);

		if (isUpdated) {
			return true;
		} else {
			throw new WebUpdateDataException("Unable to update: " + userUpdateDTO.getName());
		}
	}

}
