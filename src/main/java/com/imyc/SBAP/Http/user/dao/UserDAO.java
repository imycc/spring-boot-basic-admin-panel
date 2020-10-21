package com.imyc.SBAP.Http.user.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.imyc.SBAP.Http.role.model.Roles;
import com.imyc.SBAP.Http.user.model.Users;
import com.imyc.SBAP.Http.user.persistent.object.UserPO;

@Repository
public class UserDAO {

	private UserRepository userRepo;

	@Autowired
	public UserDAO(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public Optional<UserPO> getByUsername(String username) {

		Optional<Users> optionalUser = userRepo.findByUsername(username);

		if (optionalUser.isPresent()) {
			Users user = optionalUser.get();

			List<String> roleList = new ArrayList<String>();
			for (Roles role : user.getRoles()) {
				roleList.add(role.getName());
			}

			UserPO userPO = new UserPO();

			userPO
				.setUsername(user.getUsername())
				.setPassword(user.getPassword())
				.setDisabled(user.isDisabled())
				.setAccountExpired(user.isAccountExpired())
				.setAccountLocked(user.isAccountLocked())
				.setCredentialsExpired(user.isCredentialsExpired())
				.setRoles(roleList.toArray(new String[0]));
			return Optional.of(userPO);
		}else{
			return Optional.empty();
		}
	}
}
