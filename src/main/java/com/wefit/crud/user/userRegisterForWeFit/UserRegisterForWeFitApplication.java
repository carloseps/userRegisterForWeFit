package com.wefit.crud.user.userRegisterForWeFit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.wefit.crud.user.userRegisterForWeFit.repository")
@EntityScan(basePackages = "com.wefit.crud.user.userRegisterForWeFit.entity")
public class UserRegisterForWeFitApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserRegisterForWeFitApplication.class, args);
	}

}
