package com.imyc.SBAP;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.imyc.SBAP.Http.role.model.Roles;
import com.imyc.SBAP.Http.role.repo.RolesRepository;
import com.imyc.SBAP.Http.user.model.Users;
import com.imyc.SBAP.Http.user.repository.UserRepository;

@Component
class DemoCommandLineRunner implements CommandLineRunner{

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private UserRepository usersRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		Users user = new Users();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin") );
        user.setName("Admin");
        user.setEmail("test@test.com");
        user.setDisabled(false);
        user.setAccountExpired(false);
        user.setAccountLocked(false);
        user.setCredentialsExpired(false);
        usersRepository.save(user);

        // create three courses
        Roles roles = new Roles();
        roles.setAdmin(true);
        roles.setName("ADMIN");
        rolesRepository.save(roles);

        // add courses to the student
        user.getRoles().addAll(Arrays.asList(roles));

        // update the student
        usersRepository.save(user);
	}
}