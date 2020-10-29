package com.imyc.SBAP.Http.user.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.imyc.SBAP.Http.user.dao.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users>{

	Optional<Users> findByUsername(String username);

}
