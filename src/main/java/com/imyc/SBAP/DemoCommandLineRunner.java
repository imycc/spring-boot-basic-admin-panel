package com.imyc.SBAP;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.imyc.SBAP.Http.role.model.Roles;
import com.imyc.SBAP.Http.role.repo.RoleRepository;
import com.imyc.SBAP.Http.user.dao.Users;
import com.imyc.SBAP.Http.user.dao.repository.UserRepository;

@Component
class DemoCommandLineRunner implements CommandLineRunner{

	@Autowired
	private RoleRepository rolesRepository;

	@Autowired
	private UserRepository usersRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		Date date = new Date();
		
		Users user = new Users();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin") );
        user.setName("Admin");
        user.setEmail("test@test.com");
        user.setDisabled(false);
        user.setAccountExpired(false);
        user.setAccountLocked(false);
        user.setCredentialsExpired(false);
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
        usersRepository.save(user);

        // create three courses
        Roles role = new Roles();
        role.setAdmin(true);
        role.setName("ADMIN");
        role.setCreatedAt(date);
        role.setUpdatedAt(date);
        rolesRepository.save(role);

        Roles role2 = new Roles();
        role2.setAdmin(false);
        role2.setName("USER");
        role2.setCreatedAt(date);
        role2.setUpdatedAt(date);
        rolesRepository.save(role2);
        
        // add courses to the student
        user.getRoles().addAll(Arrays.asList(role));

        // update the student
        usersRepository.save(user);
	}
}