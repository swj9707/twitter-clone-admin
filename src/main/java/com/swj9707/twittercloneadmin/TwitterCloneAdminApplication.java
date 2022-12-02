package com.swj9707.twittercloneadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TwitterCloneAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterCloneAdminApplication.class, args);
	}
}
