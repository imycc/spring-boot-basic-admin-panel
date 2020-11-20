package com.imyc.SBAP;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.imyc.SBAP.Http.privilege.Privilege;
import com.imyc.SBAP.Http.privilege.dao.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.imyc.SBAP.Http.role.dao.Role;
import com.imyc.SBAP.Http.role.dao.repository.RoleRepository;
import com.imyc.SBAP.Http.user.dao.User;
import com.imyc.SBAP.Http.user.dao.repository.UserRepository;

@Component
class DemoCommandLineRunner implements CommandLineRunner{

	@Autowired
	private RoleRepository rolesRepository;

	@Autowired
	private UserRepository usersRepository;

	@Autowired
    private PrivilegeRepository privilegeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		Date date = new Date();
		
		User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setName("Admin");
        user.setEmail("test@test.com");
        user.setDisabled(false);
        user.setAccountExpired(false);
        user.setAccountLocked(false);
        user.setCredentialsExpired(false);
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
        usersRepository.save(user);

        Role role = new Role();
        role.setAdmin(true);
        role.setName("ADMIN");
        role.setCreatedAt(date);
        role.setUpdatedAt(date);
        rolesRepository.save(role);

        Role role2 = new Role();
        role2.setAdmin(false);
        role2.setName("USER");
        role2.setCreatedAt(date);
        role2.setUpdatedAt(date);
        rolesRepository.save(role2);

        Privilege privilege = new Privilege();
        privilege.setGroup("Dashboard");
        privilege.setName("READ");
        privilegeRepository.save(privilege);

        user.setRoles(Set.copyOf(Arrays.asList(role)));
        usersRepository.save(user);

        role.setPrivileges(Set.copyOf(Arrays.asList(privilege)));
        rolesRepository.save(role);
	}
}