package com.imyc.SBAP.Http.user.services.dpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.imyc.SBAP.Http.role.model.Roles;
import com.imyc.SBAP.Http.user.dao.model.Users;
import com.imyc.SBAP.Http.user.dao.repository.UserRepository;
import com.imyc.SBAP.Http.user.viewobject.UserVO;

@Repository
public class UserDPO {

	private UserRepository userRepo;

	@Autowired
	public UserDPO(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public Optional<UserVO> getUserByUsername(String username) {

		Optional<Users> optionalUser = userRepo.findByUsername(username);

		if (optionalUser.isPresent()) {
			Users user = optionalUser.get();

			List<String> roleList = new ArrayList<String>();
			for (Roles role : user.getRoles()) {
				roleList.add(role.getName());
			}

			UserVO userVO = new UserVO();

			userVO
				.setUsername(user.getUsername())
				.setPassword(user.getPassword())
				.setDisabled(user.isDisabled())
				.setAccountExpired(user.isAccountExpired())
				.setAccountLocked(user.isAccountLocked())
				.setCredentialsExpired(user.isCredentialsExpired())
				.setRoles(roleList.toArray(new String[0]));
			
			return Optional.of(userVO);
		}else{
			return Optional.empty();
		}
	}
}
