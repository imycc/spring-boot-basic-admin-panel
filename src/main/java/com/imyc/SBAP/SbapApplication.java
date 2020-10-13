package com.imyc.SBAP;

import java.util.Arrays;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@SpringBootApplication
public class SbapApplication {
	

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SbapApplication.class, args);

//		Flyway flyway = Flyway.configure().dataSource("jdbc:mysql://localhost:8889/sbap", "root", "root").load();
//		flyway.migrate();
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
