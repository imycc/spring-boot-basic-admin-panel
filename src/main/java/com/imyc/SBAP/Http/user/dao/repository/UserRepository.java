package com.imyc.SBAP.Http.user.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.imyc.SBAP.Http.user.dao.User;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>{

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

}
