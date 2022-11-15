package com.bookorange.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "prod");
        SpringApplication.run(ApiApplication.class, args);
    }

}
