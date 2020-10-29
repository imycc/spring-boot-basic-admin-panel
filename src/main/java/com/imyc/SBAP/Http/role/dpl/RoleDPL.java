package com.imyc.SBAP.Http.role.dpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.imyc.SBAP.Http.role.model.Roles;
import com.imyc.SBAP.Http.role.repo.RoleRepository;
import com.imyc.SBAP.Http.role.viewobject.RoleVO;

public class RoleDPL {

	private RoleRepository roleRepo;

	@Autowired
	public RoleDPL(RoleRepository roleRepo) {
		this.roleRepo = roleRepo;
	}
	

}
