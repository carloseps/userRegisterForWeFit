package com.wefit.crud.user.userRegisterForWeFit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean(name="applicationName")
    public String applicationName(){
        return "UserRegisterForWeFit";
    }
}
