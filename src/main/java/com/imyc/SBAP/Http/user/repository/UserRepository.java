package com.imyc.SBAP.Http.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.imyc.SBAP.Http.user.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users>{

	Optional<Users> findByUsername(String username);

}
