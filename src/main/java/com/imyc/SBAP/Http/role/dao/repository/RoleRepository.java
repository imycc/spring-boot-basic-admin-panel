package com.imyc.SBAP.Http.role.dao.repository;

import com.imyc.SBAP.Http.role.dao.Roles;
import com.imyc.SBAP.Http.user.dao.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleRepository extends JpaRepository<Roles, Integer>, JpaSpecificationExecutor<Roles> {

	
	
}
