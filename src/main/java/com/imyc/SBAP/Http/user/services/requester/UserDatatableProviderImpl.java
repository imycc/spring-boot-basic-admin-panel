package com.imyc.SBAP.Http.user.services.requester;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.imyc.SBAP.Exception.web.WebDeleteDataException;
import com.imyc.SBAP.Exception.web.WebPageNotFoundException;
import com.imyc.SBAP.Http.user.dto.UserCreateDTO;
import com.imyc.SBAP.Http.user.services.dpl.UserDatatableDPO;
import com.imyc.SBAP.Http.user.services.requester.contracts.UserCreateRequester;
import com.imyc.SBAP.Http.user.services.requester.contracts.UserDeleteRequester;
import com.imyc.SBAP.Http.user.services.requester.contracts.UserIndexRequester;
import com.imyc.SBAP.Http.user.services.requester.contracts.UserReadRequester;
import com.imyc.SBAP.Http.user.viewobject.UserCreateVO;
import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import com.imyc.SBAP.Http.user.viewobject.UserReadVO;

@Service
@Qualifier(value="UserDatatableProvider")
public class UserDatatableProviderImpl implements UserCreateRequester, UserDeleteRequester, UserIndexRequester, UserReadRequester  {

	private UserDatatableDPO userDatatableDPO;

	@Autowired
	public UserDatatableProviderImpl(UserDatatableDPO userDatatableDPO) {
		this.userDatatableDPO = userDatatableDPO;
	}

	@Override
	public UserDatatableVO indexResponse(HashMap<String, Object> serverSideConfig) {

		UserDatatableVO userDatatableVO = userDatatableDPO.getUserDatatableVO(serverSideConfig);

		return userDatatableVO;
	}

	@Override
	public UserReadVO readResponse(int id) throws WebPageNotFoundException {

		Optional<UserReadVO> optionalUserReadVO = userDatatableDPO.getUserDetailForRead(id);

		if (optionalUserReadVO.isPresent()) {
			return optionalUserReadVO.get();
		} else {
			throw new WebPageNotFoundException();
		}
	}

	@Override
	public boolean deleteUser(int id) throws WebDeleteDataException {

		boolean isDeleted = userDatatableDPO.deleteUserWithRelationById(id);
		
		if (isDeleted) {
			return true;
		} else {
			throw new WebDeleteDataException("Unable to delete item: " + id);
		}
	}

	@Override
	public UserCreateVO createResponse() {

		UserCreateVO userCreateVO = userDatatableDPO.getRoleListForUserCreate();
		
		return userCreateVO;
	}

	@Override
	public boolean createRequest(UserCreateDTO userCreateDTO) {
		
//		String name = parameter.get("name")[0];
//		String email = parameter.get("email")[0];
//		String password = parameter.get("password")[0];
//		String passwordConfirmation = parameter.get("password_confirmation")[0];
//		String[] roles = parameter.get("roles[]");
//		
//		List<Integer> roleIdList = new ArrayList<Integer>();
//		for(String id: roles) {
//			roleIdList.add(Integer.parseInt(id));
//		}
//
//		UserCreateDTO userCreateDTO = new UserCreateDTO();
//		userCreateDTO.setName(name)
//		.setEmail(email)
//		.setPassword(password)
//		.setPassword_confirmation(passwordConfirmation)
//		.setRoleIdList(roleIdList);
		return false;
	}

}
