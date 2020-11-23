package com.imyc.SBAP.Http.user.services.dataprocess;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.imyc.SBAP.Http.privilege.dao.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.imyc.SBAP.Http.role.dao.Role;
import com.imyc.SBAP.Http.user.dao.User;
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

		Optional<User> optionalUser = userRepo.findByUsername(username);

		if (optionalUser.isPresent()) {
			User user = optionalUser.get();

			List<String> roleList = new ArrayList<String>();
			List<Privilege> collection = new ArrayList<>();
			for (Role role : user.getRoles()) {
				roleList.add(role.getName());
				collection.addAll(role.getPrivileges());
			}
			List<String> privilegeList = new ArrayList<>();
			for (Privilege item : collection) {
				privilegeList.add(item.getName());
			}

			UserVO userVO = new UserVO();

			userVO
				.setUsername(user.getUsername())
				.setPassword(user.getPassword())
				.setDisabled(user.isDisabled())
				.setAccountExpired(user.isAccountExpired())
				.setAccountLocked(user.isAccountLocked())
				.setCredentialsExpired(user.isCredentialsExpired())
				.setRoles(roleList.toArray(new String[0]))
				.setPrivilege(privilegeList);
			
			return Optional.of(userVO);
		}else{
			return Optional.empty();
		}
	}
}
