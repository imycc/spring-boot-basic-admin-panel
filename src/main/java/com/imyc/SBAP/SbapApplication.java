package com.imyc.SBAP;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SbapApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SbapApplication.class, args);
	}
	
    @PostConstruct
    public void init(){
      // Setting Spring Boot SetTimeZone
      TimeZone.setDefault(TimeZone.getTimeZone("Asia/Taipei"));
    }

}
