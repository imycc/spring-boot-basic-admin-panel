package com.imyc.SBAP;


import java.util.Arrays;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.imyc.SBAP.Http.role.model.Roles;
import com.imyc.SBAP.Http.role.repo.RolesRepository;
import com.imyc.SBAP.Http.user.model.Users;
import com.imyc.SBAP.Http.user.repo.UsersRepository;

@SpringBootApplication
public class SbapApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SbapApplication.class, args);

//		Flyway flyway = Flyway.configure().dataSource("jdbc:mysql://localhost:8889/sbap", "root", "root").load();
//		flyway.migrate();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Bean
    public CommandLineRunner mappingDemo(UsersRepository usersRepository,
                                         RolesRepository rolesRepository) {
        return args -> {

            // create a student
            Users user = new Users();
            user.setUsername("admin");
            user.setPassword(passwordEncoder().encode("admin") );
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
        };
    }
    

//	@Bean
//	public FilterRegistrationBean simpleCorsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		// *** URL below needs to match the Vue client URL and port ***
//		config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
//		config.setAllowedMethods(Collections.singletonList("*"));
//		config.setAllowedHeaders(Collections.singletonList("*"));
//		source.registerCorsConfiguration("/**", config);
//		FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
//		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//		return bean;
//	}
}
