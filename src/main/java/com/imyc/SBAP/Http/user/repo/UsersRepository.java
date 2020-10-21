package com.imyc.SBAP.Http.user.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imyc.SBAP.Http.user.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{

	Optional<Users> findByUsername(String username);
	
}
