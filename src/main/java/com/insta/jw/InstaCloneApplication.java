package com.insta.jw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
public class InstaCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstaCloneApplication.class, args);
	}

}
